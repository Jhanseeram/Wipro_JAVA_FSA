class Util{

    getDate(){
        const date = new Date();
        return `${date.getDate()}-${date.getMonth() + 1}-${date.getFullYear()}`;
    }

    convertC2F(celsius) {
        return (celsius * 9 / 5) + 32;
    }

    getPI() {
        return Math.PI;
    }

    getFibonacci(n) {
        if (n <= 0) return [];
        if (n === 1) return [0];
        const fib = [0, 1];
        for (let i = 2; i < n; i++) {
            fib.push(fib[i - 1] + fib[i - 2]);
        }
        return fib;
    }

}
let util=new Util();

console.log(util.getDate())
console.log(util.convertC2F(25))
console.log(util.getPI())
console.log(util.getFibonacci(5))   