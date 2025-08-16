function pair<T, U>(first: T, second: U): [T, U] {
    return [first, second];
}

console.log(pair(1, "one"));
console.log(pair("hello", true));