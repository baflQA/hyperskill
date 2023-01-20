let data = [5, -13, 3, 21, 0, 9];

let sum = 0;
data.forEach(item => {
    sum += item;
});

console.log(isFinite(sum));