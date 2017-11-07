$.extend($.fn.validatebox.defaults.rules, {
    equals: {
        validator: function(value,param){
            return value == $(param[0]).val();
        },
        message: '两次输入的密码不一致'
    },
    mobile: {// 手机电话号码
        validator: function (value) {
            return /^(?:\(?[0\+]?\d{1,3}\)?)[\s-]?(?:0|\d{1,4})[\s-]?(?:(?:13\d{9})|(?:\d{7,8}))$/i.test(value);
        },
        message: '手机号码格式不正确'
    }
});