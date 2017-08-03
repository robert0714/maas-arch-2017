node("cd") {
    def serviceName = "{{ item.service_name }}"
    def swarmIp = "192.168.57.50"

    def flow = load "/data/scripts/workflow-util.groovy"
    def instances = flow.getInstances(serviceName, swarmIp).toInteger() + scale.toInteger()
    flow.putInstances(serviceName, swarmIp, instances)
    build job: "service-redeploy", parameters: [[$class: "StringParameterValue", name: "serviceName", value: serviceName]]
}
