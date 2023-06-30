import org.opennms.netmgt.model.OnmsSnmpInterface

if (! this.binding.variables.containsKey("transaction") || ! transaction) {
    return node
} else {
    for(iface in node.getSnmpInterfaces()) {
        if (node.getSysObjectId().matches("^\\.1\\.3\\.6\\.1\\.4\\.1\\.2281\\.1\\.7\\..*") && iface.?getIfAlias().?length() == 0 && iface.?getIfName().?length() == 0 && iface.?getIfDescr().?length() > 0) { //If this is a Ceragon IP10 with blank ifName and ifAlias on an SNMP interface
            LOG.warn("Node '" + node.getLabel() + "' has interface with description '" + iface.getIfDescr() + "' while ifAlias & ifName are blank.  Copying ifDescr to ifAlias.")
            iface.setIfAlias(iface.getIfDescr())
        }
    }
}
return node;
