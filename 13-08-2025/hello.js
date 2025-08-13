// function fn() {
//     let x=document.getElementById("input").value;
//     console.log(x.length)
// }


// function count(){
// let text = document.getElementById("userinput").value;
//     let rem = 50 - text.length;
//     document.getElementById("remaining").innerText = rem + " characters remaining";
// }

// arr = ["ram","peter parker","bruce","tony stark","steve rogers"];
// function filterNames() {
//     const filteredUppercase = arr
//     .filter(name => name.length > 5)
//     .map(name => name.toUpperCase());
//     console.log(filteredUppercase);
// }
// filterNames();

let residentlist=[{
    "name": "ram",
    "age": 23,
    "city": "guntur"

},
{
    "name": "peter parker",
    "age": 15,
    "city": "queens"
},
{
    "name": "bruce",
    "age": 35,
    "city": "gotham"
},
{
    "name": "tony stark",
    "age": 10,
    "city": "new york"
},
{
    "name": "steve rogers",
    "age": 45,
    "city": "brooklyn"
}
]



residentlist = residentlist.map(resident => ({
    resident,
    status: resident.age >= 18 ? "adult" : "minor"
}));

// for(let i = 0; i < residentlist.length; i++) {
//     if(residentlist[i].age>=18)
//     {
//         residentlist[i].status = "adult";
//     }else
//     {
//         residentlist[i].status = "minor";
//     } 
//  }
 console.log(residentlist);



// let eligibility = residentlist.filter(resident => resident.age >= 18);
// console.log(eligibility);