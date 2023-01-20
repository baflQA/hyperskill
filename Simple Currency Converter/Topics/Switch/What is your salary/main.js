function getSalary(value) {
    switch (value) {
        case "Bootstrap":
            console.log("$15 per hour");
            break;
        case "Chrome Extension":
            console.log("$20 per hour");
            break;
        case "React":
            console.log("$30 per hour");
            break;
        default:
            console.log("$25 per hour")
    }
}