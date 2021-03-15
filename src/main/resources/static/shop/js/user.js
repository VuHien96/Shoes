$(document).ready(function () {
    let dataLogin = {};
    let dataRegister = {};
    $("#loginForm").submit(function (e) {
        e.preventDefault();
    }).validate({
        rules: {
            login_email: {
                required: true,
                email: true,
                maxlength: 15
            },
            login_password: {
                required: true,
                rangelength: [6, 20]
            }
        },
        messages: {
            login_email: {
                required: "Vui lòng nhập email!",
                email: "Email không đúng định dạng!",

            },
            login_password: {
                required: "Vui lòng nhập mật khẩu!",
                rangelength: "Mật khẩu có độ dài từ 6-20 ký tự!",
            }
        },

        submitHandler: function () {
            dataLogin.email = $("#login_email").val();
            dataLogin.password = $("#login_password").val();

            axios.post("/api/login", dataLogin).then(function (data) {
                toastr.success("Đăng nhập thành công");
                signedValidate(true, data.data.fullName);
                // location.reload();
                $('.modal').modal('hide');
            }, function (data) {
                toastr.warning("Tài khoản hoặc mật khẩu không chính xác!");
            })
        }

    });

    $("#registerForm").submit(function (e) {
        e.preventDefault();
    }).validate({
        rules: {
            register_full_name: {
                required: true,
                maxlength: 25
            },
            register_phone: {
                required: true,
                phone: true
            },
            register_email: {
                required: true,
                email: true,
                maxlength: 25
            },
            register_password: {
                required: true,
                rangelength: [6, 25]
            },
            register_confirm_password: {
                required: true,
                equalTo: "#register_password",
                rangelength: [6, 25]
            }
        },
        messages: {
            register_full_name: {
                required: "Vui lòng nhập đầy đủ họ và tên!",
                maxlength: "Tên có độ dài tối đa 25 ký tự!",

            },
            register_phone: {
                required: "Vui lòng nhập số điện thoại!",
            },
            register_email: {
                required: "Vui lòng nhập email!",
                email: "Email không đúng định dạng!",
                maxlength: "Email có độ dài tối đa 25 ký tự!",
            },
            register_password: {
                required: "Vui lòng nhập mật khẩu!",
                rangelength: "Mật khẩu có độ dài từ 6-20 ký tự!"
            },
            register_confirm_password: {
                required: "Vui lòng nhập lại mật khẩu!",
                equalTo: "Mật khẩu không trùng nhau!",
                rangelength: "Mật khẩu có độ dài từ 6-20 ký tự!"
            }
        },

        submitHandler: function () {
            dataRegister.fullName = $("#register_full_name").val();
            dataRegister.phone = $("#register_phone").val();
            dataRegister.email = $("#register_email").val();
            dataRegister.password = $("#register_password").val();

            axios.post("/api/register",dataRegister).then(function (data){
                toastr.success("Đăng ký thành công");
                signedValidate(true, data.data.fullName);
                // location.reload();
                $('.modal').modal('hide');
            }, function (data) {
                toastr.warning("Đăng ký tài khoản không thành công!");
            })
        }
    })
});

$.validator.addMethod("phone", function (value, element) {
    return this.optional(element) || /((09|03|07|08|05)+([0-9]{8})\b)/g.test(value);
}, "Số điện thoại không hợp lệ!")

function signedValidate(status = false, fullname = '') {
    if (status == true) {
        isLogined = true;
        let signedLink = `
     <a href="#" id="account-setting" data-toggle="modal">Xin chào ${fullname}</a>`;
        $('.account-setting').replaceWith(signedLink);
    } else {
        isLogined = false;
        let notSignedLink = `
              <a href="#" data-toggle="modal" data-target="#exampleModal" class="header-icon account-setting"><i class="icon-user-2"></i></a>
          `;
        $('.account-setting').replaceWith(notSignedLink);
    }
}