import {User, UserList} from "./src/user.js"
import {checkBalance, updatePin, withdraw, deposit}  from "./src/dollars_bank_atm.js";
import promptSync from "prompt-sync";
const prompt = promptSync();

let userList = new UserList()
//userList.addUser(new User(1234, 500, ['Initial deposit of $500']))

console.log("\x1B[32m DOLLARSBANK ATM WELCOMES YOU!!\x1b[0m")

var b1 = new Boolean(true)
while(b1){
    var input = prompt('\x1b[36mEnter a Valid Choice (1 > Transaction 2 > Open New Account  3 > exit)\x1b[0m');

    if(input == 1 ){
        input = prompt("\x1b[36mPlease enter your PIN:\x1b[0m")
        if(userList.getUserByPin(input)){
            let user = userList.getUserByPin(input)
            var b2 = new Boolean(true)
            while(b2){
                console.log("\x1b\n[36m --- Transaction menu --- \n\x1b[0m")
                console.log("\x1b[36m1: Account Balance Check \n\x1b[0m")
                console.log("\x1b[36m2: Print Transactions \n\x1b[0m")
                console.log("\x1b[36m3: Update PIN \n\x1b[0m")
                console.log("\x1b[36m4: Withdraw Amount \n\x1b[0m")
                console.log("\x1b[36m5: Deposit Amount \n\x1b[0m")
                console.log("\x1b[36m6: Exit \n\x1b[0m")


                input = prompt("\x1b[36mEnter choice between 1-6\x1b[0m")

                if(input == 1){
                    checkBalance(user)
                }
                else if(input == 2){
                    if(user.transactions.length > 5){
                        console.log(user.transactions.slice(Math.max(user.transactions.length - 5, 0)))
                    }
                    else{
                        // console.log(user.transactions.toString(), '\n')
                        console.log(user.transactions)

                    }
                    //console.log(user.transactions.toString(), '\n')
                }
                else if(input == 3){
                    updatePin(user)
                }            
                else if(input == 4){
                    input = prompt("\x1b[36mHow much do you want to withdraw? \x1b[0m")
                    withdraw(user, input)
                }            
                else if(input == 5){
                    input = prompt("\x1b[36mHow much do you want to Deposit?\x1b[0m ")
                    deposit(user, input)
                }
                else if(input == 6){
                    b2 = false
                    
                }

            }
        }
        else{
            console.log("\x1b[31mInvalid Pin! \x1b[0m")
        }

    }
    else if(input == 2){
        input = prompt("\x1b[36mPlease enter initial deposit:\x1b[0m")
        let init = input

        var b3 = new Boolean(true)
        while(b3){
            input = prompt("\x1b[36mPlease enter secure pin:\x1b[0m")
            let pin1 = input
            input = prompt("\x1b[36mPlease verify pin:\x1b[0m")
            let pin2 = input
            if(pin1 == pin2){
                console.log("\x1b[36mAccount created\x1b[0m")
                userList.addUser(new User(pin1, init, [`Initial deposit of: ${init}`]))
                b3 = false
            }
            else{
                console.log("\x1b[31mPins do not match, try again!\x1b[0m")
            }
        }
    }
    else if(input == 3){
        b1 = false
    }
    else{
        console.log("\x1b[31mInvalid Input\x1b[0m")
    }

}


