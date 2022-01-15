let index = {
    init: function () {
        $("#btn-save").on("click", () => { // this를 바인딩하기 위해서!!
            this.save();
        });
        $("#btn-update").on("click", () => {
            this.update();
        });
    },

    save: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        $.ajax({
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), // http body data
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            dataType: "json" // 응답을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 String (생긴 게 json이라면) => javascript 객체로 변경
        }).done(function (resp) {
            if (resp.status === 500) {
                alert("회원가입에 실패했습니다.");
             }

            else {
                alert("회원가입이 완료 되었습니다.");
                location.href="/";
            }

        }).fail(function (error) {
            alert(JSON.stringify(error));

        });
    },

    update: function () {
        let data = {
            id: $("#id").val(),
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        $.ajax({
            type: "PUT",
            url: "/user",
            data: JSON.stringify(data), // http body data
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            dataType: "json" // 응답을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 String (생긴 게 json이라면) => javascript 객체로 변경
        }).done(function (resp) {
            alert("회원수정이 완료 되었습니다.");
            location.href="/";
        }).fail(function (error) {
            alert(JSON.stringify(error));

        });
    }
}

index.init();