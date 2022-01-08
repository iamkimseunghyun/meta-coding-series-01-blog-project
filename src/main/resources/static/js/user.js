let index = {
    init: function () {
        $("#btn-save").on("click", () => { // this를 바인딩하기 위해서!!
            this.save();
        });
    },

    save: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        // ajax 호출시 default가 비동기 호출
        // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!
        $.ajax({
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), // http body data
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            dataType: "json" // 응답을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 String (생긴 게 json이라면) => javascript 객체로 변경
        }).done(function (resp) {
            alert("회원가입이 완료 되었습니다.");
            location.href="/";
        }).fail(function (error) {
            alert(JSON.stringify(error));

        });
    }
}

index.init();