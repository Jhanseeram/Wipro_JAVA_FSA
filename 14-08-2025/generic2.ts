class Box<T> {
    value: T;

    constructor(value: T) {
        this.value = value;
    }

    getValue(): T {
        return this.value;
    }
}
const numberBox = new Box<number>(123);
const stringBox = new Box<string>("hello");

console.log(numberBox.getValue());
console.log(stringBox.getValue());