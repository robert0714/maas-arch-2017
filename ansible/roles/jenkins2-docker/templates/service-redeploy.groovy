node("cd") {
    def prodIp = "192.168.57.50"
    def swarmIp = "192.168.57.50"
    def proxyNode = "swarm-master"
    def swarmPlaybook = "swarm-healing-centos.yml"
    def proxyPlaybook = "swarm-proxy.yml"

    def flow = load "/data/scripts/workflow-util.groovy"
    def currentColor = flow.getCurrentColor(serviceName, prodIp)
    def instances = flow.getInstances(serviceName, swarmIp)

    deleteDir()
    git url: "https://github.com/robert0714/${serviceName}.git"
    try {
        flow.provision(swarmPlaybook)
        flow.provision(proxyPlaybook)
    } catch (e) {}

    flow.deploySwarm(serviceName, prodIp, currentColor, instances)
    flow.updateBGProxy(serviceName, proxyNode, currentColor)
}
