var checkUrl = location.href;
var registers = checkUrl.split("/");
var register = registers[3];


function isUserExist() {
    var telephone = $(".username").val();
    var flag = 0;
    $.ajax({
        type: "get",
        url: "/isUserExist",
        dataType: "json",
        async: false,
        data: {
            telephone: telephone
        },

        success: function (data) {
            if (data['status'] == 200) {
                flag = 1;
            }

        }

    })

    return flag;
}


function loginSubmitCheck() {

    var check = 1;
    var username = $(".username").val();
    var password = $(".password").val();
    console.info(username);
    console.info(password);
    if (username == "" || password == "") {
        //1正确，2错误
        layer.alert('必填项不能为空', {
            icon: 2,
            skin: 'layer-ext-moon'
        })
        return false;
    }

    var data = {};
    data.password = password;
    data.username = username;
    $.ajax({
        type: "post",
        url: "/login",
        dataType: "json",
        contentType: "application/json",
        async: false,
        data: JSON.stringify(data),
        success: function (data) {

            console.info(data);
            if (data.code == 500) {
                layer.alert('用户名或密码错误', {
                    icon: 2,
                    skin: 'layer-ext-moon'
                })

                check = 0;

            }

        }


    })

    if(check==0){
        return false;
    }

    return true;
}

$(".username").blur(function () {
    var check = checkPhone();
    $(".icon-double-telephone").removeClass("am-icon-check");
    $(".icon-double-telephone").removeClass("am-icon-close");
    if (check == true) {
        $(".icon-double-telephone").addClass("am-icon-check")
    }

    else {
        $(".icon-double-telephone").addClass("am-icon-close");
    }

})


$(".submit-box").on("click", function () {

    // var flag = isUserExist();
    // if(flag==1){
    //     if($(".submit-box div").hasClass("submit-icon")==true){
    //         $(".submit-box div").removeClass("submit-icon");
    //         $(".submit-box div").addClass("icon-loading");
    //
    //         $("#submit").attr('value', "登录中...");
    //
    //     }
    //
    //     else {
    //         $(".submit-box div").addClass("submit-icon");
    //         $(".submit-box div").removeClass("icon-loading");
    //     }
    // }
    //
    // else {
    //     layer.alert('未注册用户',{
    //         icon:7,
    //         skin: 'layer-ext-moon'
    //     })
    // }


})

$(".sign-in-box").on("click", function () {
    var telephone = $(".username").val();
    var telephoneCode = $(".telephone-code").val();
    var realName = $("#real-userName").val();
    var password = $(".password").val();

    if (telephone == "" || telephoneCode == "" || realName == "" || password == "") {
        layer.alert('必填项不能为空', {
            icon: 7,
            skin: 'layer-ext-moon'
        })

        return;
    }

    $.ajax({
        type: "post",
        url: "/register",

        dataType: "json",
        data: {
            providerId: telephone,
            telephoneCode: telephoneCode,
            name: realName,
            password: password
        },

        success: function (data) {
            if (data['status'] == 200) {
                layer.alert('注册成功', {
                    icon: 1,
                    skin: 'layer-ext-moon'
                })
            }

            else {
                layer.alert('注册失败', {
                    icon: 2,
                    skin: 'layer-ext-moon'
                })
            }

            resetInput();
            resetIcon();

            $(".password-box,.submit-box.submit-box,.lost-password,.sign-in").removeClass("box-hidden");

            $(".reset-box,.return-back,.code-box,.new-password,.check-new-password,.sign-in-box,.real-username-box").addClass("box-hidden");
        }
    })
})

$(".reset-box").on("click", function () {
    var telephone = $(".username").val();
    var telephoneCode = $(".telephone-code").val();
    var newPassword = $("#new-password").val();
    var check_newPassword = $("#check-new-password").val();

    if (telephone == "" || telephoneCode == "" || newPassword == "" || check_newPassword == "") {
        layer.alert('必填项不能为空', {
            icon: 2,
            skin: 'layer-ext-moon'
        })

        return;
    }

    else {
        checkNewPassword();

        $.ajax({
            type: "post",
            url: "/resetPassword",
            dataType: "json",
            data: {
                providerId: telephone,
                telephoneCode: telephoneCode,
                password: check_newPassword
            },

            success: function (data) {
                if (data['status'] == 200) {
                    layer.alert('重置成功', {
                        icon: 1,
                        skin: 'layer-ext-moon'
                    })


                }

                else {
                    layer.alert('重置失败', {
                        icon: 2,
                        skin: 'layer-ext-moon'
                    })
                }


            }
        })

    }

})


$(".lost-password").on("click", function () {
    resetInput();
    resetIcon();

    $(".reset-box,.return-back,.code-box,.new-password,.check-new-password").removeClass("box-hidden");

    $(".password-box,.submit-box,.lost-password,.sign-in").addClass("box-hidden")
})







