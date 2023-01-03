/*******************************************************************************
 *  © 2007-2021 - LogicMonitor, Inc. All rights reserved.
 ******************************************************************************/
import groovy.json.JsonSlurper

hostname = hostProps.get("system.hostname")
port = hostProps.get("haproxy.port") ?: 80
data = new URL("http://${hostname}:${port}/haproxy?stats;json;norefresh").getText()
objectdata = new JsonSlurper().parseText(data)

objectdata.each{site ->
    site.each{field ->
        if (field.field.name == "pxname"){
            println("${field.value.value}##${field.value.value}")
        }
    }
}


return 0