class User{
    constructor(pin, balance, transactions){
        this.pin = pin;
        this.balance = balance;
        this.transactions = transactions;
    }

}

class UserList{
    constructor(users = []){
        this.list = users;
    }
    addUser(user){
        this.list.push(user);
    }
    getUserByPin(pin) {
        return this.list.find(user => user.pin == pin);
    }


}
export{
    User, 
    UserList
}
