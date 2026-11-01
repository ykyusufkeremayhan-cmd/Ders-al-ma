package com.example.model

enum class GradeLevel(val number: Int, val title: String, val tag: String, val description: String) {
    GRADE_5(5, "5. Sınıf", "Ortaokula İlk Adım", "Temel kavramlar, zihinden işlemler, fen keşifleri ve yeni Maarif becerileri."),
    GRADE_6(6, "6. Sınıf", "Gelişim Düzeyi", "Kesirler, açılar, ekosistemler, tarihin ilk izleri ve derinleşen öğrenme."),
    GRADE_7(7, "7. Sınıf", "Hazırlık Düzeyi", "Rasyonel sayılar, cebir, kuvvet ve enerji, Osmanlı tarihi ve analitik düşünme."),
    GRADE_8(8, "8. Sınıf", "LGS & Maarif Zirve", "Çarpanlar ve katlar, DNA ve genetik, İnkılap tarihi ve kapsamlı LGS denemeleri.");

    companion object {
        fun fromNumber(number: Int?): GradeLevel = entries.find { it.number == number } ?: GRADE_5
    }
}
