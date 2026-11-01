package com.example.model

enum class SchoolType(val id: String, val title: String, val subtitle: String, val badge: String) {
    DEVLET(
        id = "devlet",
        title = "Devlet Ortaokulu",
        subtitle = "Türkiye Yüzyılı Maarif Modeli standart ortaokul müfredatı ve tüm temel dersler.",
        badge = "Standart MEB"
    ),
    IMAM_HATIP(
        id = "imam_hatip",
        title = "İmam Hatip Ortaokulu",
        subtitle = "Temel Maarif müfredatına ek olarak Kur'an-ı Kerim, Siyer, Temel Dini Bilgiler ve Arapça dersleri.",
        badge = "İHO Özel Müfredat"
    );

    companion object {
        fun fromId(id: String?): SchoolType = entries.find { it.id == id } ?: DEVLET
    }
}
