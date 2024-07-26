const cookies={


    getCookie(cName){
    const name = cName + "=";
    const decode = decodeURIComponent(document.cookie);
    const cArr = decode.split("; ");
    let value = "empty";
    cArr.forEach(val =>{
        if(val.indexOf(name) === 0){
            value = decodeURIComponent(val.substring(name.length));
        }
    })
    console.log(value)
    return value;
    },

    setCookiesInDays(name, value, expDays){
        let data = new Date();
        data.setTime(data.getTime() + (expDays * 24 * 60 * 60 * 1000));
        const expires = "expires=" + data.toUTCString();
        document.cookie = name + "=" + encodeURIComponent(value) + "; " + expires + "; path=/";
    },


}

window.cookies = cookies;
