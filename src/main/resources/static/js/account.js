// 当前页码
let currentPage = 1;

/**
 * 根据所在页查询账户信息
 *
 * @param page 当前页码
 */
function loadAccounts(page) {
    $.ajax({
        url: '/api/oaiAccount',
        data: {pageNum: page},
        success: function (data) {
            currentPage = data.current;
            var total = data.total;
            let size = data.size;

            var hasPreviousPage = true;
            var hasNextPage = true;
            if (currentPage <= 1) {
                hasPreviousPage = false;
            }
            if (currentPage * size >= total) {
                hasNextPage = false;
            }


            $('#accountTable tbody').empty();

            let startSerial = (currentPage - 1) * size + 1;
            data.records.forEach((account, index) => {
                $('#accountTable tbody').append(`
                        <tr>
                            <td>${startSerial + index}</td>
                            <td>${account.id}</td>
                            <td>${account.email}</td>
                            <td>${account.password}</td>
                            <td>${account.validStatus}</td>
                            <td>${account.createTime}</td>
                            <td>${account.updateTime}</td>
                            <td>
                                <button class="btn btn-secondary" onclick="showAccountForm('${account.id}')">Edit</button>
                            </td>
                            <td><button class="btn btn-danger" onclick="deleteAccount('${account.id}')">Delete</button></td>
                        </tr>
                    `);
            });
            $('#paginationInfo').text(`Page: ${data.current} of ${data.pages}`);

            toggleButtonState('#prevPage', hasPreviousPage);
            toggleButtonState('#nextPage', hasNextPage);
        },
        error: function (jqXHR) {
            alert('Failed to load accounts: ' + jqXHR.responseText);
        }
    });
}

function toggleButtonState(buttonId, isEnabled) {
    const button = $(buttonId);
    if (isEnabled) {
        button.prop('disabled', false);
        button.addClass('btn-green');
    } else {
        button.prop('disabled', true);
        button.removeClass('btn-green');
    }
}

function showAccountForm(id) {
    clearFormValidation();
    // 如果存在主键，则为更新操作
    if (id) {
        $.ajax({
            url: `/api/oaiAccount/${id}`,
            success: function (data) {
                $('#accountId').val(data.id);
                $('#email').val(data.email);
                $('#password').val(data.password);
                $('#validStatus').val(data.validStatus);
                // $('#formMethod').val('PUT');
                $('#accountModal').modal('show');
            },
            error: function (jqXHR) {
                alert('Failed to load account: ' + jqXHR.responseText);
            }
        });
    } else {
        $('#accountId').val('');
        $('#email').val('');
        $('#password').val('');
        $('#validStatus').val('');
        // $('#formMethod').val('POST');
        $('#accountModal').modal('show');
    }
}

/**
 * 保存账户：更新/新增
 */
function saveAccount() {
    if (!validateForm()) {
        return;
    }
    const account = {
        email: $('#email').val(),
        password: $('#password').val(),
        validStatus: $('#validStatus').val()
    };
    const id = $('#accountId').val();
    // const method = $('#formMethod').val();
    var method = 'POST';
    if (id) {
        method = 'PUT';
        account.id = id;
    }

    $.ajax({
        url: '/api/oaiAccount',
        method: method,
        contentType: 'application/json',
        data: JSON.stringify(account),
        success: function () {
            $('#accountModal').modal('hide');
            loadAccounts(currentPage);
        },
        error: function (jqXHR) {
            alert(`Failed to ${method === 'PUT' ? 'update' : 'create'} account: ` + jqXHR.responseText);
        }
    });
}

/**
 * 删除账户
 *
 * @param id
 */
function deleteAccount(id) {
    if (!confirm('Are you sure you want to delete this account?')) {
        return;
    }
    $.ajax({
        url: `/api/oaiAccount/${id}`,
        method: 'DELETE',
        success: function () {
            loadAccounts(currentPage);
        },
        error: function (jqXHR) {
            alert('Failed to delete account: ' + jqXHR.responseText);
        }
    });
}

function validateForm() {
    let isValid = true;
    $('#accountForm .form-control').each(function () {
        if (!this.checkValidity()) {
            $(this).addClass('is-invalid');
            isValid = false;
        } else {
            $(this).removeClass('is-invalid');
        }
    });
    return isValid;
}

function clearFormValidation() {
    $('#accountForm .form-control').removeClass('is-invalid');
}

$(document).ready(function () {
    loadAccounts(currentPage);
});