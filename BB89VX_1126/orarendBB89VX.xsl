<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html" encoding="UTF-8" indent="yes" />

    <xsl:template match="/">
        <html>
            <body>
                <h2>Orarend adatai</h2>
                <table border="4">
                    <thead>
                        <tr bgcolor="#9acd32">
                            <th>ID</th>
                            <th>Típus</th>
                            <th>Tárgy</th>
                            <th>Idopont</th>
                            <th>Helyszin</th>
                            <th>Oktato</th>
                            <th>Szak</th>
                            <th>Oraado</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="orarend/ora">
                            <tr>
                                <td><xsl:value-of select="@id" /></td>
                                <td><xsl:value-of select="@tipus" /></td>
                                <td><xsl:value-of select="targy" /></td>
                                <td><xsl:value-of select="idopont" /></td>
                                <td><xsl:value-of select="helyszin" /></td>
                                <td><xsl:value-of select="oktato" /></td>
                                <td><xsl:value-of select="szak" /></td>
                                <td><xsl:value-of select="oraado" /></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
