node("cd") {
    git url: "https://github.com/robert0714/${serviceName}.git"
    dockerFlow(serviceName, ["scale", "proxy"], ["--scale=\"" + scale + "\""])
}
