
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
