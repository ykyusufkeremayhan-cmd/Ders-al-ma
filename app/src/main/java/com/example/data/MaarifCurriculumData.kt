package com.example.data

import com.example.model.QuizQuestion
import com.example.model.Subject
import com.example.model.Topic

object MaarifCurriculumData {

    // Subjects list
    val allSubjects: List<Subject> = listOf(
        Subject(
            id = "turkce",
            name = "Türkçe",
            shortName = "Türkçe",
            iconKey = "book",
            colorHex = "#2563EB",
            isImamHatipOnly = false,
            grades = listOf(5, 6, 7, 8),
            description = "Söz varlığı, okuduğunu anlama, dil bilgisi ve Maarif modeline uygun metin tahlilleri."
        ),
        Subject(
            id = "matematik",
            name = "Matematik",
            shortName = "Matematik",
            iconKey = "calculate",
            colorHex = "#D97706",
            isImamHatipOnly = false,
            grades = listOf(5, 6, 7, 8),
            description = "Sayılar ve işlemler, cebirsel düşünme, geometri, veri işleme ve analitik problem çözme."
        ),
        Subject(
            id = "fen",
            name = "Fen Bilimleri",
            shortName = "Fen Bil.",
            iconKey = "science",
            colorHex = "#059669",
            isImamHatipOnly = false,
            grades = listOf(5, 6, 7, 8),
            description = "Dünya ve evren, canlılar ve yaşam, madde ve doğası, fiziksel olaylar ve bilimsel sorgulama."
        ),
        Subject(
            id = "sosyal",
            name = "Sosyal Bilgiler / İnkılap Tarihi",
            shortName = "Sosyal Bil.",
            iconKey = "public",
            colorHex = "#DC2626",
            isImamHatipOnly = false,
            grades = listOf(5, 6, 7, 8),
            description = "Kültür ve miras, etkin vatandaşlık, üretim-dağıtım-tüketim ve Milli Mücadele ruhu."
        ),
        Subject(
            id = "ingilizce",
            name = "İngilizce",
            shortName = "İngilizce",
            iconKey = "translate",
            colorHex = "#7C3AED",
            isImamHatipOnly = false,
            grades = listOf(5, 6, 7, 8),
            description = "İletişim odaklı yabancı dil becerileri, kelime bilgisi, diyaloglar ve kültürel etkileşim."
        ),
        Subject(
            id = "din",
            name = "Din Kültürü ve Ahlak Bilgisi",
            shortName = "Din Kültürü",
            iconKey = "menu_book",
            colorHex = "#0D9488",
            isImamHatipOnly = false,
            grades = listOf(5, 6, 7, 8),
            description = "İnanç, ibadet, ahlak değerleri, peygamber kıssaları ve evrensel adalet anlayışı."
        ),
        // İmam Hatip Specific Subjects:
        Subject(
            id = "kuran",
            name = "Kur'an-ı Kerim",
            shortName = "Kur'an",
            iconKey = "auto_stories",
            colorHex = "#0284C7",
            isImamHatipOnly = true,
            grades = listOf(5, 6, 7, 8),
            description = "Harflerin mahreçleri, tecvid kuralları, ezber sureler, anlam ve tefekkür."
        ),
        Subject(
            id = "siyer",
            name = "Peygamberimizin Hayatı",
            shortName = "Siyer",
            iconKey = "favorite",
            colorHex = "#16A34A",
            isImamHatipOnly = true,
            grades = listOf(5, 6, 7, 8),
            description = "Peygamber Efendimiz Hz. Muhammed'in (s.a.v.) çocukluğu, gençliği, ahlakı ve nebevi metodu."
        ),
        Subject(
            id = "temel_dini",
            name = "Temel Dini Bilgiler",
            shortName = "Temel Dini",
            iconKey = "psychology",
            colorHex = "#EA580C",
            isImamHatipOnly = true,
            grades = listOf(5, 6, 7, 8),
            description = "İslam inanç esasları, fıkhi hükümler, helal-haram bilinci ve toplumsal sorumluluk."
        ),
        Subject(
            id = "arapca",
            name = "Arapça",
            shortName = "Arapça",
            iconKey = "record_voice_over",
            colorHex = "#9333EA",
            isImamHatipOnly = true,
            grades = listOf(5, 6, 7, 8),
            description = "Harfler ve sesletim, tanışma, selamlaşma, okul hayatı ve temel diyalog kalıpları."
        )
    )

    // Topics list across 5, 6, 7, 8 grades
    val sampleTopics: List<Topic> = listOf(
        // GRADE 5 - TÜRKÇE
        Topic(
            id = "turkce_g5_t1",
            subjectId = "turkce",
            grade = 5,
            unitName = "1. Tema: Birey ve Toplum",
            title = "Sözcükte Anlam (Gerçek, Mecaz, Terim)",
            subtitle = "Kelimelerin derinliği ve zengin anlam dünyası",
            summaryBullets = listOf(
                "Gerçek Anlam (Temel Anlam): Kelimenin akla gelen ilk ve en yaygın anlamıdır.",
                "Mecaz Anlam: Kelimenin gerçek anlamından tamamen uzaklaşarak kazandığı yeni ve soyut anlamdır.",
                "Terim Anlam: Bilim, sanat, spor veya meslek dalına özgü özel kavramları karşılayan kelimelerdir.",
                "Eş Anlamlı & Zıt Anlamlı Sözcükler: Anlamdaş ve karşıt kavramların cümledeki görevleri."
            ),
            fullContent = """
                ### 1. Gerçek Anlam (Temel Anlam)
                Bir sözcüğün zihnimizde uyandırdığı ilk ve asıl anlamdır. Sözlükte daima 1. sırada yer alır.
                * **Örnek:** 'Çocuğun elindeki sıcak çay döküldü.' (Buradaki sıcak, ısısı yüksek anlamındadır ve gerçektir.)
                * **Örnek:** 'Kuşun kırık kanadını sardılar.' (Fiziksel parçalanma gerçektir.)

                ### 2. Mecaz Anlam
                Bir kelimenin gerçek anlamından bütünüyle sıyrılarak, benzetme veya ilgi yoluyla soyut bir duygu/durumu anlatmasıdır.
                * **Örnek:** 'Bize karşı çok **sıcak** davrandı.' (Sıcak burada samimi ve içten anlamındadır; ısı ölçülemez!)
                * **Örnek:** 'Sert sözleriyle kalbimi **kırdı**.' (Gönül incitmek anlamında mecazdır.)

                ### 3. Terim Anlam
                Belli bir bilim, teknik, spor veya sanat alanına mahsus kavramlardır.
                * **Örnek:** Matematikte: *üçgen, açı, hipotenüs*.
                * **Örnek:** Müzikte: *nota, solfej, akor*.
                * **Örnek:** Tiyatroda: *perde, dekor, suflör*.
            """.trimIndent(),
            maarifNote = "Türkiye Yüzyılı Maarif Modeli: Dil, düşüncenin evidir. Kelimeleri yerinde ve zengin anlam katmanlarıyla kullanmak zihinsel esnekliği artırır.",
            keyFormulasOrRules = listOf(
                "İpucu: Mecaz anlamda kelime fiziksel özelliklerini kaybedip duyguya dönüşür.",
                "Uyarı: Günlük hayatta kullanılan genel kelimeler terim sayılamaz."
            ),
            estimatedReadMinutes = 5,
            xpReward = 50
        ),

        // GRADE 5 - MATEMATİK
        Topic(
            id = "matematik_g5_t1",
            subjectId = "matematik",
            grade = 5,
            unitName = "1. Ünite: Doğal Sayılar ve İşlemler",
            title = "Milyonlu Sayılar ve Bölük Kavramı",
            subtitle = "Büyük sayıları okuma, basamak değeri ve zihinden işlemler",
            summaryBullets = listOf(
                "Doğal sayılar sağdan sola doğru üçerli gruplara (bölüklere) ayrılır.",
                "Bölükler: Birler Bölüğü, Binler Bölüğü, Milyonlar Bölüğü.",
                "Basamak Değeri: Bir rakamın bulunduğu basamağa göre aldığı değerdir.",
                "Sayı Değeri: Rakamın basamağına bakılmaksızın kendi değeridir."
            ),
            fullContent = """
                ### Milyonlar Bölüğünü Tanıyalım
                7, 8 ve 9 basamaklı sayıları doğru okumak için sayıları sağdan sola üçerli gruplara ayırırız. Bu gruplara **bölük** denir.
                
                | Milyonlar Bölüğü | Binler Bölüğü | Birler Bölüğü |
                | :---: | :---: | :---: |
                | Yüz M. / On M. / Milyon | Yüz B. / On B. / Bin | Yüzler / Onlar / Birler |

                **Örnek Sayı:** 84.502.316
                * Okunuşu: *Seksen dört milyon beş yüz iki bin üç yüz on altı*
                * Milyonlar Bölüğü: 84
                * Binler Bölüğü: 502
                * Birler Bölüğü: 316
                
                **Önemli Kural:** Sayı okunurken her bölükteki sayı okunur ve ardından o bölüğün adı söylenir. Yalnızca **birler bölüğünün** adı söylenmez!
            """.trimIndent(),
            maarifNote = "Maarif Modeli Becerisi: Sayı hissi ve örüntü kavrama. Gerçek hayat verilerini (nüfus, gezegen mesafeleri) matematiksel olarak modelleme becerisi kazandırır.",
            keyFormulasOrRules = listOf(
                "Bölükleme kuralı: Daima en sağdan 3'erli grupla!",
                "Sıfır olan bölükler okunurken bölük adı atlanmaz, doğrudan sonraki bölüğe geçilir."
            ),
            estimatedReadMinutes = 6,
            xpReward = 50
        ),

        // GRADE 5 - FEN BİLİMLERİ
        Topic(
            id = "fen_g5_t1",
            subjectId = "fen",
            grade = 5,
            unitName = "1. Ünite: Güneş, Dünya ve Ay",
            title = "Güneş ve Ay'ın Yapısı ve Özellikleri",
            subtitle = "Gök cisimlerinin geometrisi, hareketleri ve evreleri",
            summaryBullets = listOf(
                "Güneş orta büyüklükte, küresel bir yıldızdır ve hidrojen gazının helyuma dönüşmesiyle ısı-ışık üretir.",
                "Ay, Dünya'mızın tek doğal uydusudur ve atmosferi yok denecek kadar incedir.",
                "Ay'ın ana evreleri: Yeni Ay, İlk Dördün, Dolunay, Son Dördün.",
                "Ay'ın kendi etrafında dönme süresi ile Dünya etrafında dolanma süresi eşit (~27.3 gün) olduğu için Dünya'dan hep aynı yüzü görülür."
            ),
            fullContent = """
                ### 1. Güneş'in Özellikleri
                * Samanyolu galaksisinde bulunan orta büyüklükte sarı bir yıldızdır.
                * Şekli küreseldir. Kendi ekseni etrafında saat yönünün tersine (batıdan doğuya) döner.
                * Yüzey sıcaklığı yaklaşık 6.000 °C, çekirdek sıcaklığı ise 15 milyon °C'dir.
                * Üzerinde daha soğuk bölgeler olan 'Güneş Lekeleri' (Sunspots) bulunur.

                ### 2. Ay'ın Özellikleri ve Evreleri
                * Ay bir ışık kaynağı değildir; Güneş'ten aldığı ışığı yansıtır.
                * Atmosferi olmadığı için rüzgâr, yağmur gibi hava olayları görülmez.
                * Gece ile gündüz arasındaki sıcaklık farkı çok büyüktür.
                * Gök taşlarının çarpması sonucu kraterler oluşmuştur.
            """.trimIndent(),
            maarifNote = "Maarif Modeli Değeri: Merak ve bilimsel sorgulama. Evrendeki mükemmel ahengi ve fiziksel döngüleri gözlem yoluyla fark etme.",
            keyFormulasOrRules = listOf(
                "Ay'ın Evreleri Arası Süre: Bir ana evreden diğerine geçiş yaklaşık 1 haftadır (7 gün).",
                "Güneş > Dünya > Ay büyüklük sıralaması (Basketbol topu > Ceviz > Mercimek benzetmesi)."
            ),
            estimatedReadMinutes = 5,
            xpReward = 50
        ),

        // GRADE 6 - MATEMATİK
        Topic(
            id = "matematik_g6_t1",
            subjectId = "matematik",
            grade = 6,
            unitName = "1. Ünite: Sayılar ve İşlemler",
            title = "Kesirlerle İşlemler ve Karşılaştırma",
            subtitle = "Payda eşitleme, modelleme ve problem stratejileri",
            summaryBullets = listOf(
                "Paydaları eşit kesirlerde payı büyük olan kesir daha büyüktür.",
                "Payları eşit kesirlerde paydası küçük olan kesir daha büyüktür.",
                "Toplama ve çıkarmada paydalar mutlaka eşitlenmelidir.",
                "Çarpma işleminde paylar çarpılıp paya, paydalar çarpılıp paydaya yazılır."
            ),
            fullContent = """
                ### Kesirleri Karşılaştırma Stratejileri
                1. **Payda Eşitleme:** Kesirler genişletilerek paydaları eşitlenir; payı büyük olan büyüktür.
                2. **Bütüne ve Yarıma Yakınlık:** 7/8 bütüne çok yakındır, 1/8 sıfıra yakındır.
                
                ### Kesirlerde Çarpma ve Bölme
                * Çarpma: (a/b) × (c/d) = (a × c) / (b × d)
                * Bölme: Birinci kesir aynen yazılır, ikinci kesir ters çevrilip çarpılır.
            """.trimIndent(),
            maarifNote = "Maarif Modeli Becerisi: Somutlaştırma ve orantısal akıl yürütme. Parça-bütün ilişkisini günlük harcama ve paylaşımlarda uygulayabilme.",
            keyFormulasOrRules = listOf(
                "Kesir bölmesinde sihirli kural: 'Birinciyi tut, ikinciyi ters çevir ve çarp!'",
                "Tam sayılı kesirleri çarpmadan önce mutlaka bileşik kesre çevir."
            ),
            estimatedReadMinutes = 6,
            xpReward = 50
        ),

        // GRADE 7 - FEN BİLİMLERİ
        Topic(
            id = "fen_g7_t1",
            subjectId = "fen",
            grade = 7,
            unitName = "3. Ünite: Kuvvet ve Enerji",
            title = "Kütle ve Ağırlık İlişkisi, Kinetik ve Potansiyel Enerji",
            subtitle = "Yer çekimi kuvveti, iş kavramı ve enerjinin korunumu",
            summaryBullets = listOf(
                "Kütle değişmeyen madde miktarıdır; eşit kollu terazi ile ölçülür ve birimi kg'dır.",
                "Ağırlık bir kuvvettir; dinamometre ile ölçülür ve birimi Newton (N)'dur.",
                "Kinetik Enerji: Hareket halindeki cisimlerin sahip olduğu enerjidir (Ek = 1/2 * m * v²).",
                "Potansiyel Enerji: Çekim potansiyeli (yükseklik) ve esneklik potansiyeli (yaylar) olmak üzere ikiye ayrılır."
            ),
            fullContent = """
                ### Kütle ve Ağırlık Arasındaki Farklar
                * **Kütle:** Evrenin her yerinde sabittir. Dünyada da Ay'da da 60 kg olan bir cisim değişmez.
                * **Ağırlık:** Cisme etki eden yer çekimi kuvvetidir. Ay'daki çekim kuvveti Dünya'nın yaklaşık 1/6'sı olduğu için Ay'daki ağırlık 6 kat daha azdır!

                ### Fiziksel Anlamda İş
                Bir kuvvetin iş yapabilmesi için:
                1. Cisme bir kuvvet uygulanmalı,
                2. Cisim uygulanan kuvvet doğrultusunda yer değiştirmelidir.
                * Duvarı iten ama hareket ettiremeyen biri fiziksel anlamda iş yapmaz.
            """.trimIndent(),
            maarifNote = "Maarif Modeli: Enerjinin korunumu yasası. Enerji yoktan var edilemez, vardan yok edilemez; sadece biçim değiştirir.",
            keyFormulasOrRules = listOf(
                "İş = Kuvvet × Alınan Yol (W = F × Δx)",
                "Toplam Mekanik Enerji = Kinetik Enerji + Potansiyel Enerji (Sürtünmesiz ortamda sabit kalır)."
            ),
            estimatedReadMinutes = 6,
            xpReward = 50
        ),

        // GRADE 8 - MATEMATİK (LGS)
        Topic(
            id = "matematik_g8_t1",
            subjectId = "matematik",
            grade = 8,
            unitName = "1. Ünite: Çarpanlar ve Katlar",
            title = "EBOB ve EKOK Problemleri (LGS Yeni Nesil)",
            subtitle = "Asal çarpanlara ayırma ve mantık muhakeme stratejileri",
            summaryBullets = listOf(
                "Pozitif bir tam sayıyı kalansız bölen sayılara o sayının çarpanları (bölenleri) denir.",
                "EBOB (En Büyük Ortak Bölen): Parçalama, bölme, eşit poşetlere/şişelere paylaştırma sorularında kullanılır.",
                "EKOK (En Küçük Ortak Kat): Birleştirme, periyodik nöbet, ziller, kutuların üst üste dizilmesi sorularında kullanılır.",
                "Aralarında asal sayıların EBOB'u 1, EKOK'u ise çarpımlarına eşittir."
            ),
            fullContent = """
                ### LGS Yeni Nesil Soru Çözüm Rehberi
                LGS sınavında doğrudan 'EBOB kaçtır?' diye sorulmaz; hikayeleştirilmiş senaryolar verilir:

                #### 1. Ne Zaman EBOB Kullanılır? (Bütünden Parçaya)
                * Tarlanın etrafına eşit aralıklarla ağaç veya direk dikiliyorsa,
                * Farklı zeytinyağları veya pirinç çuvalları birbirine karıştırılmadan eşit hacimli kaplara dökülüyorsa,
                * Kumaşlar veya tahtalar eşit parçalara bölünecekse **EBOB** uygulanır.

                #### 2. Ne Zaman EKOK Kullanılır? (Parçadan Bütüne)
                * İki otobüs veya tren farklı dakikalarda kalkıp 'ilk kez ne zaman birlikte hareket eder?' deniyorsa,
                * Doktorlar veya hemşireler farklı günlerde nöbet tutup 'kaç gün sonra birlikte nöbet tutarlar?' deniyorsa,
                * Fayanslar yan yana konularak kare bir zemin kaplanıyorsa **EKOK** uygulanır.
            """.trimIndent(),
            maarifNote = "Türkiye Yüzyılı Maarif Modeli LGS Becerisi: Üst düzey bilişsel beceriler, problem kurma ve strateji geliştirme.",
            keyFormulasOrRules = listOf(
                "Kural: İki pozitif sayının çarpımı, bu sayıların EBOB'u ile EKOK'unun çarpımına eşittir (a × b = EBOB(a,b) × EKOK(a,b)).",
                "Aralarında asal sayılar için: EBOB(a,b) = 1 ve EKOK(a,b) = a × b."
            ),
            estimatedReadMinutes = 7,
            xpReward = 60
        ),

        // GRADE 8 - İNKILAP TARİHİ
        Topic(
            id = "sosyal_g8_t1",
            subjectId = "sosyal",
            grade = 8,
            unitName = "1. Ünite: Bir Kahraman Doğuyor",
            title = "Mustafa Kemal'in Fikir Hayatını Etkileyen Şehirler",
            subtitle = "Selanik, Manastır, İstanbul, Şam ve Sofya",
            summaryBullets = listOf(
                "Selanik: Çok uluslu yapı, zengin basın-yayın hayatı ve liman kenti olması hoşgörü ve demokratik vizyon kazandırdı.",
                "Manastır: Askeri İdadi dönemi; Namık Kemal, Mehmet Emin Yurdakul ve Fransız aydınlarıyla tanışarak vatan sevgisi pekişti.",
                "İstanbul: Harp Okulu ve Harp Akademisi yılları; ülke sorunlarını arkadaşlarıyla tartışıp el yazması gazete çıkardı.",
                "Şam: İlk görev yeri; 'Vatan ve Hürriyet Cemiyeti'ni kurarak liderlik ve teşkilatçılık kabiliyetini gösterdi.",
                "Sofya: Askeri Ataşemiliterlik dönemi; Batı diplomasisini ve parlamenter sistemi yakından inceledi."
            ),
            fullContent = """
                ### Selanik: Hoşgörü ve Özgür Düşünce Beşiği
                Mustafa Kemal'in doğup büyüdüğü Selanik, Batı'daki fikir hareketlerinin Osmanlı'ya giriş kapısıydı. Çok çeşitli etnik köken ve dinlerin bir arada yaşaması, onda farklılıklara saygı ve çağdaşlaşma bilincini geliştirdi.

                ### Manastır: Milliyetçilik ve Tarih Bilinci
                Mustafa Kemal, Manastır Askeri İdadisi'nde okurken Fransız İhtilali'nin yaydığı hürriyet, adalet ve eşitlik kavramlarıyla tanıştı. Edebiyat ve tarih öğretmeni Tevfik Bey'in etkisiyle milli tarih şuurunu kazandı.

                ### Şam ve Sofya: Teşkilatçılık ve Diplomasi
                İlk görev yeri olan Şam'da kurduğu 'Vatan ve Hürriyet Cemiyeti', onun aktif bir lider olduğunun kanıtıdır. Sofya'daki görevi ise uluslararası ilişkilerde deneyim kazanmasını sağlamıştır.
            """.trimIndent(),
            maarifNote = "Maarif Modeli Değeri: Milli bilinç, vatanseverlik ve liderlik vizyonu. Tarihten ilham alarak geleceği inşa etme şuuru.",
            keyFormulasOrRules = listOf(
                "LGS Kodlaması: S-M-İ-Ş-S (Selanik, Manastır, İstanbul, Şam, Sofya)",
                "Kavram-Olay eşleşmesi: Şam -> Teşkilatçılık; Sofya -> Diplomasi; Selanik -> Kültürel Zenginlik."
            ),
            estimatedReadMinutes = 6,
            xpReward = 60
        ),

        // İMAM HATİP ÖZEL - KUR'AN-I KERİM (GRADE 5 & 6)
        Topic(
            id = "kuran_g5_t1",
            subjectId = "kuran",
            grade = 5,
            unitName = "1. Ünite: Kur'an-ı Kerim'i Tanıyalım",
            title = "Kur'an'ın İndiriliş Süreci ve Temel Özellikleri",
            subtitle = "Vahiy, mushaf, sure ve ayet kavramları ile tecvid temelleri",
            summaryBullets = listOf(
                "Kur'an-ı Kerim, 610 yılı Ramazan ayının Kadir Gecesi'nde Hira Mağarası'nda indirilmeye başlanmıştır.",
                "İlk inen ayetler Alak Suresi'nin ilk 5 ayetidir ('Yaratan Rabbinin adıyla oku!').",
                "23 yıllık peyderpey (parça parça) indirilme hikmeti: Hükümlerin hayata kolayca uygulanması.",
                "Kur'an'da 114 sure ve yaklaşık 6666 ayet bulunmaktadır."
            ),
            fullContent = """
                ### 1. Kur'an-ı Kerim'in Temel Kavramları
                * **Ayet:** Sureleri oluşturan, başı ve sonu belli olan ilahi cümle veya ifadelerdir.
                * **Sure:** Ayetlerin oluşturduğu, besmele ile başlayan müstakil bölümlerdir. En uzun sure *Bakara*, en kısa sure *Kevser*'dir.
                * **Cüz:** Kur'an-ı Kerim'in yirmişer sayfadan oluşan her bir bölümüne (toplam 30 cüz) denir.
                * **Mushaf:** Kur'an-ı Kerim'in iki kapak arasına alınmış kitap haline denir. Hz. Ebubekir döneminde kitap haline getirilmiş, Hz. Osman döneminde çoğaltılmıştır.

                ### 2. Harflerin Mahreçleri ve Tecvidin Önemi
                Tecvid, Kur'an harflerinin çıkış yerlerine (mahreç) ve sıfatlarına riayet ederek güzel ve hatasız okunmasını sağlayan ilimdir.
            """.trimIndent(),
            maarifNote = "Maarif Modeli İmam Hatip Vizyonu: Kur'an'ı sadece lafzıyla değil, ilahi mesajı ve ahlakıyla anlayıp hayata tatbik etme şuuru.",
            keyFormulasOrRules = listOf(
                "Mushaf oluşumu: Kitaplaşma -> Hz. Ebubekir; Çoğaltılma -> Hz. Osman.",
                "İlk vahiy: 610 yılı Kadir Gecesi, Hira Mağarası, Cebrail (a.s.) vasıtasıyla."
            ),
            estimatedReadMinutes = 5,
            xpReward = 50
        ),

        // İMAM HATİP ÖZEL - PEYGAMBERİMİZİN HAYATI (SİYER - GRADE 7 & 8)
        Topic(
            id = "siyer_g7_t1",
            subjectId = "siyer",
            grade = 7,
            unitName = "2. Ünite: Medine Dönemi ve Nebevi Metot",
            title = "Hicret ve Medine İslam Toplumunun İnşası",
            subtitle = "Kardeşlik sözleşmesi (Muahat), Mescid-i Nebevi ve Medine Sözleşmesi",
            summaryBullets = listOf(
                "622 yılında Mekkeli müşriklerin baskılarına karşı Müslümanlar Medine'ye hicret etmiştir.",
                "Ensar (Medineli Müslümanlar) ve Muhacir (Mekke'den göç edenler) arasında kardeşlik bağı (Muahat) tesis edilmiştir.",
                "Mescid-i Nebevi inşa edilmiş; hem ibadet hem yönetim hem de Ashab-ı Suffe ile eğitim merkezi olmuştur.",
                "Medine Sözleşmesi ile tarihin ilk yazılı anayasası oluşturulmuş, farklı inanç gruplarının bir arada barış içinde yaşaması sağlanmıştır."
            ),
            fullContent = """
                ### 1. Hicretin Tarihi ve Manevi Anlamı
                Hicret sadece bir göç hareketi değil, adaletin, barışın ve İslam medeniyetinin temelinin atıldığı dönüm noktasıdır. Hicret ile İslam Devleti kurulmuş ve Hicri takvimin başlangıcı olmuştur.

                ### 2. Medine Sözleşmesi'nin Evrensel İlkeleri
                Peygamber Efendimiz (s.a.v.), Müslümanlar ile Medine'de yaşayan Yahudi kabileleri ve diğer unsurlar arasında bir toplumsal mutabakat metni imzalamıştır.
                * Her topluluk kendi inancında serbest olacaktır.
                * Şehre dışarıdan bir saldırı olursa ortak savunma yapılacaktır.
                * Adalet esastır, haksızlık yapan kim olursa olsun cezalandırılacaktır.
            """.trimIndent(),
            maarifNote = "Maarif Modeli Değeri: Birlikte yaşama ahlakı, adaletin üstünlüğü ve vefa bilinci.",
            keyFormulasOrRules = listOf(
                "Suffe: Mescid-i Nebevi'nin bitişiğindeki yatılı ilim meclisi (İlk İslam Üniversitesi).",
                "Muahat: Ensar ile Muhacir arasındaki tarihi kardeşlik sözleşmesi."
            ),
            estimatedReadMinutes = 6,
            xpReward = 50
        ),

        // İMAM HATİP ÖZEL - ARAPÇA (GRADE 5 & 6)
        Topic(
            id = "arapca_g5_t1",
            subjectId = "arapca",
            grade = 5,
            unitName = "1. Ünite: Selamlaşma ve Tanışma (التعارف)",
            title = "Arapça Temel Selamlaşma ve İsim Sorma Kalıpları",
            subtitle = "Merhaban, Ehlen ve Sehlen, Keyfe Haluk diyalogları",
            summaryBullets = listOf(
                "مَرْحَبًا (Merhaban) - Merhaba",
                "أَهْلًا وَسَهْلًا (Ehlen ve sehlen) - Hoş geldin(iz)",
                "مَا اسْمُكَ؟ (Mesmuke?) - (Erkeğe) Adın ne? / مَا اسْمُكِ؟ (Mesmuki?) - (Kıza) Adın ne?",
                "كَيْفَ حَالُكَ؟ (Keyfe haluke?) - Nasılsın? -> أَنَا بِخَيْرٍ، شُكْرًا (Ene bihayr, şükran) - İyiyim, teşekkürler."
            ),
            fullContent = """
                ### Temel Arapça Hitap Kalıpları
                Arapçada eril (müzekker) ve dişil (müennes) ayrımı vardır. Bu yüzden bir erkeğe veya kadına seslenirken son harfin harekesi değişir.

                * **Erkeğe:** مَا اسْمُكَ؟ (*Mesmuke?*) -> Adın nedir?
                * **Kıza:** مَا اسْمُكِ؟ (*Mesmuki?*) -> Adın nedir?
                * **Cevap:** اسْمِي يُوسُف (*İsmi Yusuf*) -> Benim adım Yusuf'tur.

                ### Sabah ve Akşam Selamları
                * صَبَاحُ الخَيْرِ (*Sabahu'l-hayr*) -> Günaydın / Hayırlı sabahlar
                * صَبَاحُ النُّورِ (*Sabahu'n-nur*) -> Cevap: Nurlu sabahlar
                * مَسَاءُ الخَيْرِ (*Mesau'l-hayr*) -> İyi akşamlar
            """.trimIndent(),
            maarifNote = "Maarif Modeli Becerisi: Çok dillilik ve kültürlerarası iletişim köprüsü kurma.",
            keyFormulasOrRules = listOf(
                "Müzekker (Erkek) için zamir: كَ (Ke) / Müennes (Dişi) için zamir: كِ (Ki)",
                "Cevap verirken daima 'Ben' anlamındaki أَنَا (Ene) veya 'Benim' anlamındaki ي (İ) iyelik eki kullanılır."
            ),
            estimatedReadMinutes = 5,
            xpReward = 50
        )
    )

    // Interactive Quiz Questions (with detailed explanation)
    val sampleQuestions: List<QuizQuestion> = listOf(
        // Q1: Türkçe (Grade 5)
        QuizQuestion(
            id = "q_turkce_1",
            topicId = "turkce_g5_t1",
            subjectId = "turkce",
            grade = 5,
            questionText = "Aşağıdaki cümlelerin hangisinde altı çizili sözcük mecaz anlamda kullanılmıştır?",
            options = listOf(
                "A) Odadaki soba odayı iyice ısıttı.",
                "B) Bize gösterdiği sıcak davranışlar hepimizi mutlu etti.",
                "C) Soğuk su içtiği için boğazı şişti.",
                "D) Güneş battıktan sonra hava serinledi."
            ),
            correctIndex = 1,
            explanation = "Doğru cevap B seçeneğidir. 'Sıcak davranışlar' ifadesindeki sıcak sözcüğü, fiziksel bir ısıyı değil; samimi, içten ve sevecen olma durumunu anlattığı için mecaz anlam kazanmıştır. Diğer seçeneklerdeki kelimeler gerçek anlamdadır.",
            hint = "Kelimenin gerçek fiziksel ısısını değil de samimiyeti anlatıp anlatmadığına dikkat et."
        ),
        // Q2: Matematik (Grade 5)
        QuizQuestion(
            id = "q_mat_1",
            topicId = "matematik_g5_t1",
            subjectId = "matematik",
            grade = 5,
            questionText = "72 045 318 doğal sayısının 'binler bölüğünde' bulunan sayı aşağıdakilerden hangisidir?",
            options = listOf(
                "A) 72",
                "B) 318",
                "C) 045 (45)",
                "D) 453"
            ),
            correctIndex = 2,
            explanation = "Doğru cevap C seçeneğidir. Sayı sağdan sola doğru bölüklere ayrıldığında: 318 birler bölüğü, 045 binler bölüğü, 72 ise milyonlar bölüğüdür. Dolayısıyla binler bölüğündeki sayı 45'tir.",
            hint = "Sağdan başlayarak üçerli basamak gruplarını ayır: Birler, Binler, Milyonlar."
        ),
        // Q3: Fen Bilimleri (Grade 5)
        QuizQuestion(
            id = "q_fen_1",
            topicId = "fen_g5_t1",
            subjectId = "fen",
            grade = 5,
            questionText = "Ay'ın Dünya'dan bakıldığında daima aynı yüzünün görünmesinin temel sebebi aşağıdakilerden hangisidir?",
            options = listOf(
                "A) Ay'ın kendi ışığını üretmeyip Güneş'ten yansıtması",
                "B) Ay'ın atmosferinin yok denecek kadar ince olması",
                "C) Ay'ın kendi etrafında dönme süresi ile Dünya etrafında dolanma süresinin eşit olması",
                "D) Dünya'nın Güneş etrafında 365 günde dolanması"
            ),
            correctIndex = 2,
            explanation = "Doğru cevap C seçeneğidir. Ay'ın kendi ekseni etrafında dönme süresi ile Dünya etrafındaki dolanma süresi birbirine eşittir (yaklaşık 27.3 gün). Bu senkronize hareket nedeniyle Dünya'dan bakan bir gözlemci Ay'ın daima aynı yüzünü görür.",
            hint = "Kendi etrafındaki dönüş süresi ile Dünya etrafındaki tur süresinin ilişkisini hatırla."
        ),
        // Q4: Matematik (Grade 6)
        QuizQuestion(
            id = "q_mat_6_1",
            topicId = "matematik_g6_t1",
            subjectId = "matematik",
            grade = 6,
            questionText = "Bir pastanın 3/8'ini Ali, 2/8'ini Ayşe yemiştir. Geriye pastanın kaçta kaçı kalmıştır?",
            options = listOf(
                "A) 5/8",
                "B) 3/8",
                "C) 1/8",
                "D) 4/8"
            ),
            correctIndex = 1,
            explanation = "Doğru cevap B seçeneğidir. Yenilen toplam miktar: 3/8 + 2/8 = 5/8'dir. Pastanın tamamı 8/8 olduğuna göre kalan miktar: 8/8 - 5/8 = 3/8'dir.",
            hint = "Bütün pastayı 8/8 olarak düşün ve yenilen parçaları toplayıp bütünden çıkar."
        ),
        // Q5: Fen Bilimleri (Grade 7)
        QuizQuestion(
            id = "q_fen_7_1",
            topicId = "fen_g7_t1",
            subjectId = "fen",
            grade = 7,
            questionText = "Dünya'da kütlesi 60 kg olan bir astronot Ay'a gittiğinde kütlesi ve ağırlığı nasıl değişir? (Ay'ın yer çekimi Dünya'nın 1/6'sıdır)",
            options = listOf(
                "A) Kütlesi 10 kg olur, ağırlığı değişmez.",
                "B) Kütlesi değişmez (60 kg kalır), ağırlığı 6 kat azalır.",
                "C) Hem kütlesi hem ağırlığı 6 kat azalır.",
                "D) Kütlesi artar, ağırlığı azalır."
            ),
            correctIndex = 1,
            explanation = "Doğru cevap B seçeneğidir. Kütle değişmeyen madde miktarıdır ve evrenin hiçbir yerinde değişmez (60 kg kalır). Ağırlık ise yer çekimine bağlı bir kuvvet olduğu için çekimin 6 kat az olduğu Ay'da ağırlık 6 kat azalır.",
            hint = "Unutma: Kütle asla değişmez! Sadece yer çekimi kuvveti olan ağırlık değişir."
        ),
        // Q6: Matematik (Grade 8 LGS)
        QuizQuestion(
            id = "q_mat_8_1",
            topicId = "matematik_g8_t1",
            subjectId = "matematik",
            grade = 8,
            questionText = "Kenar uzunlukları 24 metre ve 36 metre olan dikdörtgen şeklindeki bir bahçenin etrafına ve köşelerine eşit aralıklarla fidan dikilecektir. İki fidan arasındaki mesafe en fazla kaç metre olabilir?",
            options = listOf(
                "A) 6 m",
                "B) 8 m",
                "C) 12 m",
                "D) 18 m"
            ),
            correctIndex = 2,
            explanation = "Doğru cevap C seçeneğidir. İki fidan arası mesafenin en fazla olması istendiğinden hem 24'ü hem de 36'yı kalansız bölen en büyük sayıya (EBOB) bakılır. EBOB(24, 36) = 12 metredir.",
            hint = "Eşit parçalara ayırma ve en büyük aralık istendiği için EBOB yöntemini kullan."
        ),
        // Q7: İnkılap Tarihi (Grade 8)
        QuizQuestion(
            id = "q_ink_8_1",
            topicId = "sosyal_g8_t1",
            subjectId = "sosyal",
            grade = 8,
            questionText = "Mustafa Kemal'in ilk görev yeri olan Şam'da gizlice 'Vatan ve Hürriyet Cemiyeti'ni kurması, onun en çok hangi kişisel özelliğini gösterir?",
            options = listOf(
                "A) İleri görüşlülüğünü",
                "B) Teşkilatçılık ve liderlik kabiliyetini",
                "C) Açık sözlülüğünü",
                "D) Sanatseverliğini"
            ),
            correctIndex = 1,
            explanation = "Doğru cevap B seçeneğidir. Bir cemiyet kurup insanları ortak bir amaç etrafında örgütlemek ve bir araya getirmek doğrudan 'teşkilatçılık' ve 'liderlik' yeteneğinin bir göstergesidir.",
            hint = "İnsanları bir cemiyet çatısı altında bir araya getirme kabiliyetini düşün."
        ),
        // Q8: Kur'an-ı Kerim (İmam Hatip)
        QuizQuestion(
            id = "q_kuran_1",
            topicId = "kuran_g5_t1",
            subjectId = "kuran",
            grade = 5,
            questionText = "Kur'an-ı Kerim'in iki kapak arasına getirilerek kitap (Mushaf) haline getirildiği dönem ile çoğaltıldığı dönem hangi halifelerin zamanıdır?",
            options = listOf(
                "A) Kitap haline getirilme: Hz. Ömer - Çoğaltılma: Hz. Ali",
                "B) Kitap haline getirilme: Hz. Ebubekir - Çoğaltılma: Hz. Osman",
                "C) Kitap haline getirilme: Hz. Osman - Çoğaltılma: Hz. Ebubekir",
                "D) Kitap haline getirilme: Hz. Ali - Çoğaltılma: Hz. Ömer"
            ),
            correctIndex = 1,
            explanation = "Doğru cevap B seçeneğidir. Yemame Savaşı'nda hafızların şehit düşmesi üzerine Hz. Ebubekir döneminde Zeyd bin Sabit başkanlığında bir komisyon kurularak Kur'an Mushaf haline getirilmiş, İslam coğrafyası genişleyince Hz. Osman döneminde çoğaltılarak önemli merkezlere gönderilmiştir.",
            hint = "İlk halife Mushaf yaptı, üçüncü halife çoğalttı."
        ),
        // Q9: Peygamberimizin Hayatı (İmam Hatip)
        QuizQuestion(
            id = "q_siyer_1",
            topicId = "siyer_g7_t1",
            subjectId = "siyer",
            grade = 7,
            questionText = "Mekke'den Medine'ye hicret eden Müslümanlara ne ad verilir?",
            options = listOf(
                "A) Ensar",
                "B) Muhacir",
                "C) Havari",
                "D) Müezzin"
            ),
            correctIndex = 1,
            explanation = "Doğru cevap B seçeneğidir. Dini ve inancı uğruna Mekke'den Medine'ye göç eden kutlu Müslümanlara 'Muhacir', onlara Medine'de evlerini ve imkanlarını açarak yardım eden Medineli yerli Müslümanlara ise 'Ensar' denir.",
            hint = "Göç eden (hicret eden) anlamındaki kelimeyi hatırla."
        ),
        // Q10: Arapça (İmam Hatip)
        QuizQuestion(
            id = "q_arapca_1",
            topicId = "arapca_g5_t1",
            subjectId = "arapca",
            grade = 5,
            questionText = "Arapçada bir erkek arkadaşınıza 'Nasılsın?' diye sormak istediğinizde hangi ifadeyi kullanmalısınız?",
            options = listOf(
                "A) كَيْفَ حَالُكَ؟ (Keyfe haluke?)",
                "B) كَيْفَ حَالُكِ؟ (Keyfe haluki?)",
                "C) مَا اسْمُكَ؟ (Mesmuke?)",
                "D) مِنْ أَيْنَ أَنْتَ؟ (Min eyne ente?)"
            ),
            correctIndex = 0,
            explanation = "Doğru cevap A seçeneğidir. كَيْفَ حَالُكَ؟ (Keyfe haluke?) erkek için 'Nasılsın?' sorusudur. ك (ke) zamiri erkeği temsil eder. B seçeneğindeki كِ (ki) zamiri ise kıza (müennese) sorulurken kullanılır.",
            hint = "Erkek için kullanılan fetha harekesini (ke) ara."
        )
    )

    // Placement / Seviye Belirleme Sınavı Questions (Comprehensive 6-question multi-disciplinary test)
    val placementExamQuestions: List<QuizQuestion> = listOf(
        QuizQuestion(
            id = "diag_1",
            topicId = "placement_diag",
            subjectId = "turkce",
            grade = 0,
            questionText = "[Türkçe] 'Yazar, bu romanında kelimeleri adeta kuyumcu titizliğiyle işlemiş.' cümlesinde anlatılmak istenen temel düşünce nedir?",
            options = listOf(
                "A) Eserin çok pahalı ve lüks basıldığı",
                "B) Dilin ve anlatımın son derece özenli, titizlikle seçildiği",
                "C) Kitabın kuyumculuk mesleğini anlattığı",
                "D) Yazarın romanı çok hızlı bir şekilde yazdığı"
            ),
            correctIndex = 1,
            explanation = "Kuyumcu titizliği benzetmesi, dil ve üslup üzerinde ince eleyip sık dokumayı, her kelimeye büyük bir itina göstermeyi ifade eder.",
            isPlacement = true
        ),
        QuizQuestion(
            id = "diag_2",
            topicId = "placement_diag",
            subjectId = "matematik",
            grade = 0,
            questionText = "[Matematik] Bir manavdaki 120 kg elmanın 2/5'i sabah, kalanının 1/3'ü öğleden sonra satılmıştır. Manavda kaç kg elma kalmıştır?",
            options = listOf(
                "A) 48 kg",
                "B) 72 kg",
                "C) 48 kg kalmıştır",
                "D) 48 kg kalmıştır (Doğru hesap: 48 kg)"
            ),
            correctIndex = 2,
            explanation = "Sabah satılan: 120 x (2/5) = 48 kg. Kalan: 120 - 48 = 72 kg. Öğleden sonra satılan: 72 x (1/3) = 24 kg. Son kalan: 72 - 24 = 48 kg.",
            isPlacement = true
        ),
        QuizQuestion(
            id = "diag_3",
            topicId = "placement_diag",
            subjectId = "fen",
            grade = 0,
            questionText = "[Fen Bilimleri] Fotosentez olayı ile ilgili aşağıda verilen bilgilerden hangisi DOĞRUDUR?",
            options = listOf(
                "A) Yalnızca karanlık ortamlarda gerçekleşir.",
                "B) Karbondioksit ve su kullanılarak besin (glikoz) ve oksijen üretilir.",
                "C) Tüm canlılar fotosentez yaparak oksijen tüketir.",
                "D) Fotosentez kloroplastı olmayan hayvan hücrelerinde gerçekleşir."
            ),
            correctIndex = 1,
            explanation = "Fotosentez ışık enerjisi varlığında klorofil içeren üretici canlılarda karbondioksit (CO2) ve su (H2O) birleşerek glikoz ve oksijen (O2) üretmesidir.",
            isPlacement = true
        ),
        QuizQuestion(
            id = "diag_4",
            topicId = "placement_diag",
            subjectId = "sosyal",
            grade = 0,
            questionText = "[Sosyal / İnkılap] Misakımilli kararlarında yer alan 'Milli ve ekonomik gelişmemizi engelleyen siyasi, adli ve mali sınırlamalar kaldırılmalıdır.' maddesiyle doğrudan ne hedeflenmiştir?",
            options = listOf(
                "A) Kapitülasyonların kaldırılması ve tam bağımsızlık",
                "B) Saltanatın derhal kaldırılması",
                "C) Başka bir devletin mandası altına girmek",
                "D) Sınırların daraltılması"
            ),
            correctIndex = 0,
            explanation = "Siyasi, adli ve mali sınırlamalara 'kapitülasyon' denir. Bu maddeyle ekonomik ve siyasi egemenliğimizi kısıtlayan ayrıcalıkların reddedilerek tam bağımsızlığa ulaşılması amaçlanmıştır.",
            isPlacement = true
        ),
        QuizQuestion(
            id = "diag_5",
            topicId = "placement_diag",
            subjectId = "din",
            grade = 0,
            questionText = "[Din Kültürü] Kişinin kendi gayret ve iradesiyle seçtiği, sevap veya günahından sorumlu olduğu alana İslam inancında ne ad verilir?",
            options = listOf(
                "A) Külli İrade",
                "B) Cüzi İrade",
                "C) Ecel ve Ömür",
                "D) Tevekkül"
            ),
            correctIndex = 1,
            explanation = "Allah'ın sınırsız iradesine Külli İrade denir. İnsana verilen, tercih ve fiillerinden sorumlu tutulduğu sınırlı iradeye ise Cüzi İrade denir.",
            isPlacement = true
        ),
        QuizQuestion(
            id = "diag_6",
            topicId = "placement_diag",
            subjectId = "ingilizce",
            grade = 0,
            questionText = "[İngilizce] 'I think online classes are very useful because students can study at their own pace.' cümlesinde hangi düşünce dile getirilmiştir?",
            options = listOf(
                "A) Çevrimiçi derslerin zaman kaybı olduğu",
                "B) Öğrencilerin kendi hızlarında öğrenebildikleri için çevrimiçi derslerin faydalı olduğu",
                "C) Okula gitmenin her zaman daha eğlenceli olduğu",
                "D) Teknolojinin eğitime zarar verdiği"
            ),
            correctIndex = 1,
            explanation = "'Useful' faydalı, 'at their own pace' ise kendi öğrenme hızlarında demektir.",
            isPlacement = true
        )
    )

    // Initial Badges
    val badges = listOf(
        com.example.model.BadgeItem(
            id = "badge_first_step",
            title = "İlk Adım",
            description = "İlk konu özetini tamamladın ve öğrenme yolculuğuna başladın.",
            icon = "🌱",
            isUnlocked = true,
            unlockedDate = "Dün"
        ),
        com.example.model.BadgeItem(
            id = "badge_quiz_master",
            title = "Test Ustası",
            description = "Bir etkileşimli testi %100 doğrulukla tamamla.",
            icon = "🎯",
            isUnlocked = true,
            unlockedDate = "Bugün"
        ),
        com.example.model.BadgeItem(
            id = "badge_streak_fire",
            title = "Ateşli Seri",
            description = "3 gün üst üste uygulamada ders çalışarak seriyi koru.",
            icon = "🔥",
            isUnlocked = true,
            unlockedDate = "Bugün"
        ),
        com.example.model.BadgeItem(
            id = "badge_duel_champ",
            title = "Düello Şampiyonu",
            description = "Arkadaşınla veya Maarif Bot ile yaptığın düelloyu kazan.",
            icon = "⚔️",
            isUnlocked = false
        ),
        com.example.model.BadgeItem(
            id = "badge_placement_done",
            title = "Seviye Kâşifi",
            description = "Seviye Belirleme Sınavını çöz ve gelişim haritanı çıkar.",
            icon = "🧭",
            isUnlocked = false
        ),
        com.example.model.BadgeItem(
            id = "badge_maarif_star",
            title = "Maarif Yıldızı",
            description = "1000 XP puan barajını aşarak Üstat unvanını kazan.",
            icon = "⭐",
            isUnlocked = false
        )
    )

    // Initial Daily Quests
    val dailyQuests = listOf(
        com.example.model.DailyQuest(
            id = "quest_read_summary",
            title = "1 Konu Özeti Oku",
            targetCount = 1,
            currentCount = 1,
            xpReward = 40,
            isCompleted = true
        ),
        com.example.model.DailyQuest(
            id = "quest_solve_quiz",
            title = "En Az 1 Test Çöz",
            targetCount = 1,
            currentCount = 1,
            xpReward = 50,
            isCompleted = true
        ),
        com.example.model.DailyQuest(
            id = "quest_play_duel",
            title = "1 Kez Düello Yap",
            targetCount = 1,
            currentCount = 0,
            xpReward = 60,
            isCompleted = false
        )
    )

    // Simulated Social Leaderboard
    val mockLeaderboard = listOf(
        com.example.model.LeaderboardUser(
            rank = 1,
            name = "Zeynep Sare",
            schoolType = com.example.model.SchoolType.IMAM_HATIP,
            grade = 8,
            xp = 1840,
            streak = 14,
            avatarEmoji = "👑",
            title = "Maarif Bilgesi"
        ),
        com.example.model.LeaderboardUser(
            rank = 2,
            name = "Kerem Alp",
            schoolType = com.example.model.SchoolType.DEVLET,
            grade = 8,
            xp = 1620,
            streak = 9,
            avatarEmoji = "🚀",
            title = "LGS Fatihi"
        ),
        com.example.model.LeaderboardUser(
            rank = 3,
            name = "Elif Naz",
            schoolType = com.example.model.SchoolType.DEVLET,
            grade = 7,
            xp = 1450,
            streak = 11,
            avatarEmoji = "⭐",
            title = "Fen Dehası"
        ),
        com.example.model.LeaderboardUser(
            rank = 4,
            name = "Geleceğin Kaşifi (Sen)",
            schoolType = com.example.model.SchoolType.DEVLET,
            grade = 5,
            xp = 340,
            streak = 4,
            isCurrentUser = true,
            avatarEmoji = "🎓",
            title = "Meraklı Kaşif"
        ),
        com.example.model.LeaderboardUser(
            rank = 5,
            name = "Hamza Eren",
            schoolType = com.example.model.SchoolType.IMAM_HATIP,
            grade = 6,
            xp = 290,
            streak = 3,
            avatarEmoji = "🦁",
            title = "Çalışkan Öğrenci"
        ),
        com.example.model.LeaderboardUser(
            rank = 6,
            name = "Meryem Betül",
            schoolType = com.example.model.SchoolType.IMAM_HATIP,
            grade = 7,
            xp = 260,
            streak = 2,
            avatarEmoji = "🌸",
            title = "İlim Yolcusu"
        ),
        com.example.model.LeaderboardUser(
            rank = 7,
            name = "Yusuf Kaan",
            schoolType = com.example.model.SchoolType.DEVLET,
            grade = 5,
            xp = 210,
            streak = 2,
            avatarEmoji = "⚡",
            title = "Hızlı Çözücü"
        )
    )
}
