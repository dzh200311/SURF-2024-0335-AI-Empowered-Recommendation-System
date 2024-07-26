

const authentication = {

    checkUserAuthentication(){
        storeHistory();

        var shortToken = localStorage.getItem("tempToken");

        if(shortToken == null){
            window.location.href = "http://120.26.136.194:8080/subPage/User/Login.html";
        }else {
            $.ajax({
                url: "http://120.26.136.194:8000/token/validate",
                method: "POST",
                data: { token: shortToken },
                success: function(response) {
                    if (response === false) {
                        authentication.refreshUserToken();
                    }
                }
            })
        }

    },

    checkStaffAuthentication(){
        storeHistory();

        var shortToken = localStorage.getItem("tempToken");

        if(shortToken == null){
            window.location.href = "http://120.26.136.194:8080/subPage/User/Manager.html";
        }else {
            $.ajax({
                url: "http://120.26.136.194:8000/token/validate",
                method: "POST",
                data: { token: shortToken },
                success: function(response) {
                    if (response === false) {
                        authentication.refreshStaffToken();
                    }
                }
            })
        }

    },


    getLongTermToken(callback) {

        var token = localStorage.getItem("tempToken");
        console.log(token);
        $.ajax({
            url: "http://120.26.136.194:8000/token/generate_long_token",
            method: "POST",
            data: { token: token },
            success: function(response) {
                console.log("response:" + response)

                localStorage.setItem("longTermToken", response);
                callback();
            },
        })
    },

    refreshUserToken(){
        $.ajax({
            url: "http://120.26.136.194:8000/token/refresh_token",
            method: "POST",
            data: { token: localStorage.getItem("longTermToken") },
            success: function(response) {
                if (response === "Invalid token") {
                    window.location.href = "http://120.26.136.194:8080/subPage/User/Login.html";
                }else {
                    console.log("refreshed token")
                    location.reload()
                    localStorage.setItem("tempToken", response);
                }

            },
        })
    },

    refreshStaffToken(callback) {
        console.log("refreshing staff token")
        $.ajax({
            url: "http://120.26.136.194:8000/token/refresh_token",
            method: "POST",
            data: { token: localStorage.getItem("longTermToken") },
            success: function(response) {
                if (response === "Invalid token") {
                    alert("Invalid token");
                    window.location.href = "http://120.26.136.194:8080/subPage/User/Manager.html";
                } else {
                    console.log("refreshed token")
                    location.reload()
                    localStorage.setItem("tempToken", response);
                }
            },
            error: function(xhr, status, error) {
                console.error("Error refreshing token:", error);
                // Handle error case if needed
            }
        });
    }

}

function setCookiesInMinutes(name, value, expMinutes){
    let data = new Date();
    data.setTime(data.getTime() + (expMinutes * 1000 * 60));
    const expires = "expires=" + data.toUTCString();
    document.cookie = name + "=" + encodeURIComponent(value) + "; " + expires + "; path=/";
}

function storeHistory(){
    var url = window.location.href;
    console.log(url);
    setCookiesInMinutes("history", url, 5)
}
