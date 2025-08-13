document.addEventListener('DOMContentLoaded', function() {
    const num1 = document.getElementById('num1');
    const num2 = document.getElementById('num2');
    const result = document.getElementById('result');

    function calculate(op) {
        const a = Number(num1.value);
        const b = Number(num2.value);
        let res;
        switch(op) {
            case 'add':
                res = a + b;
                break;
            case 'sub':
                res = a - b;
                break;
            case 'mul':
                res = a * b;
                break;
            case 'div':
                if (b === 0) {
                    res = "Cannot divide by zero";
                } else {
                    res = a / b;
                }
                break;
            default:
                res = "Invalid operation";
        }
        result.value = res;
    }

    document.getElementById('addBtn').onclick = function() { calculate('add'); };
    document.getElementById('subBtn').onclick = function() { calculate('sub'); };
    document.getElementById('mulBtn').onclick = function() { calculate('mul'); };
    document.getElementById('divBtn').onclick = function() { calculate('div'); };
});