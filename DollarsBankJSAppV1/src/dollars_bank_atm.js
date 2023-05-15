
import promptSync from "prompt-sync";
const prompt = promptSync();

//check balance
function checkBalance(user){
    console.log('$', user.balance)

}
// //print transactions
// export function printTransactions(){

// }
//update pin
function updatePin(user){
    var input = prompt("\x1b[36mEnter old pin: \x1b[0m")
    if(input == user.pin){
        input = prompt("\x1b[36mEnter new pin: \x1b[0m")
        user.pin = input
        console.log(`\x1b[36mUser pin updated to : ${input} \x1b[0m`)

    }
    else{
        console.log("\x1b[31mWrong Pin! \x1b[0m")
    }

}

//withdraw amount
function withdraw(user, p1){
    let wit = Number(p1)
    let bal = Number(user.balance)
    if(wit > bal){
        console.log("\x1b[31mInsufficient Funds! \x1b[0m")
    }
    else{
        let newBal = bal - wit
        user.balance = newBal
        console.log(`\x1b[36mNew Balance : ${newBal} \x1b[0m`)

        // user.transactions.push(`\x1B[31mWithdrew ${wit}, new Balance : ${newBal} \x1b[0m`)
        user.transactions.push(`Withdrew ${wit}, new Balance : ${newBal} `)

    }

}

//deposit amoount
function deposit(user, p1){
    let dep = Number(p1)
    let bal = Number(user.balance)
    let newBal = bal + dep
    user.balance = newBal
    console.log(`\x1b[36mNew Balance : ${newBal} \x1b[0m`)
    // user.transactions.push(`\x1B[32mDeposited ${dep}, new Balance : ${newBal} \x1b[0m`)
    user.transactions.push(`Deposited ${dep}, new Balance : ${newBal} `)


}

export{
    checkBalance, 
    updatePin, 
    withdraw, 
    deposit
}