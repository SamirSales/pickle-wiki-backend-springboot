
export const userHasPermission = function(user, permissionValue){
    if(user){
        for(let permission of user.permissions){
            if(permission.permissionType === permissionValue){
                return true;
            }
        }
    }
  
    return false;
}

export const validateEmail = function(email){
    // eslint-disable-next-line 
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}
