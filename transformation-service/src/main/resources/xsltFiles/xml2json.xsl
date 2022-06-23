<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"   xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <xsl:text>
"PurchaseOrder":{
</xsl:text>
        <xsl:variable name="orderDate" select="/PurchaseOrder/@OrderDate"/>
        <!-- The variable orderDate can be used for further processing.  -->


        <xsl:text>"OrderDate":"</xsl:text> <xsl:value-of select="$orderDate"/><xsl:text>",
 </xsl:text>
        <xsl:text>"OrderId":"</xsl:text> <xsl:value-of select="/PurchaseOrder/@OrderId"/><xsl:text>",
 </xsl:text>
        <xsl:for-each select="/PurchaseOrder/Address">
<xsl:text>
"Address":{
</xsl:text>
            <xsl:text>"Type":"</xsl:text> <xsl:value-of select="./@Type"/><xsl:text>",
 </xsl:text>
            <xsl:text>
"Name":{
</xsl:text>
            <xsl:value-of   select="./Name"/>
            <xsl:text>},
</xsl:text>
            <xsl:text>
"Street":{
</xsl:text>
            <xsl:value-of   select="./Street"/>
            <xsl:text>},
</xsl:text>
            <xsl:text>
"City":{
</xsl:text>
            <xsl:value-of   select="./City"/>
            <xsl:text>},
</xsl:text>
            <xsl:text>
"State":{
</xsl:text>
            <xsl:value-of   select="./State"/>
            <xsl:text>},
</xsl:text>
            <xsl:text>
"Zip":{
</xsl:text>
            <xsl:value-of   select="./Zip"/>
            <xsl:text>},
</xsl:text>
            <xsl:text>
"Country":{
</xsl:text>
            <xsl:value-of   select="./Country"/>
            <xsl:text>},
</xsl:text>
            <xsl:text>},
</xsl:text>
        </xsl:for-each>
        <xsl:text>
"DeliveryNotes":{
</xsl:text>
        <xsl:value-of   select="/PurchaseOrder/DeliveryNotes"/>
        <xsl:text>},
</xsl:text>
        <xsl:text>
"Items":{
</xsl:text>
        <xsl:for-each select="/PurchaseOrder/Items/Item">
<xsl:text>
"Item":{
</xsl:text>
            <xsl:text>"PartNumber":"</xsl:text> <xsl:value-of select="./@PartNumber"/><xsl:text>",
 </xsl:text>
            <xsl:text>
"ProductName":{
</xsl:text>
            <xsl:value-of   select="./ProductName"/>
            <xsl:text>},
</xsl:text>
            <xsl:text>
"Quantity":{
</xsl:text>
            <xsl:value-of   select="./Quantity"/>
            <xsl:text>},
</xsl:text>
            <xsl:text>
"USPrice":{
</xsl:text>
            <xsl:value-of   select="./USPrice"/>
            <xsl:text>},
</xsl:text>
            <xsl:text>
"Discount":{
</xsl:text>
            <xsl:value-of   select="./Discount"/>
            <xsl:text>},
</xsl:text>
            <xsl:text>
"Comment":{
</xsl:text>
            <xsl:value-of   select="./Comment"/>
            <xsl:text>},
</xsl:text>
            <xsl:text>},
</xsl:text>
        </xsl:for-each>
        <xsl:text>},
</xsl:text>
        <xsl:text>}
</xsl:text>
        <xsl:text>}</xsl:text>
    </xsl:template>
    <xsl:output method="xml" omit-xml-declaration="yes" />
</xsl:stylesheet>
