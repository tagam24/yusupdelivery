package com.tagam24.tagam;

import android.content.Context;
import android.os.Handler;

import com.tagam24.tagam.Constants.Constants;
import com.tagam24.tagam.Database.Db;


/**
 * Created by User on 26.07.2018.
 */

public class dil {
    public static String dili;
    Db db;
    public Handler s;

    public dil() {
        s = new Handler();
    }

    String[] listItems = new String[2];
    public static String garashylyar,kabul_edildi,kabul_edilmedi,yolda,dostawleno,shtuk, unregister, registrasiya, adynyzy_girizin, telefon_belginizi_girizini, salgynyzy_girizin, tagam_eltip_bermek, maglumat_yok,menin_zakazym,belginiz,
            sahsy_otag, belgini_dogry_girizin, belgini_tassykla, kody_girizin, Eltip_Bermek_Hyzmaty1, Arzanladyshlar, Arzanladyshlar1, Kafe_Bar,gumenje,adynyz,salgynyz,
            zakaz_taza,zakaz_kona,txt_status_order,gowy,erbet,kanagatlanarly,oran_gowy,ajayyp,tagamy_bahalndyryn,mynasyp_baha_bermeginizi_hayys,tassykla,min_zakaz,
            Kafe_Bar1, Toy_Hymzatlar, Toy_Hymzatlar1, Salatlar, Saslyklar, Garnirlar, Tortlar, Icgiler, Corbalar, Gozleg, guymenje1, guymenje2, Sushi,resepti,tayyarlansy,
            first_page,
            Pizza, Burger, Tiz_tagam, Shashlyk, Balyk, Salat, Desert, Buz_gaymak, Ichgi, Irdenlik, Gunortanlyk, manat, Corba, yza, dili_sayla, tm_dili, ru_dili, tema, Kombo, Gyzgyn_nahar, Garnir,
            Hamyrly_nahar, Mangal, Doner,yapyk,tm_ru,cafe,beylekiler,tagamlar,bahalandy,kafeny_bahalandyryn,gyzyklandyrar,eltip_bermek,zakazy_tassykla,karzina,welcome,tassyklama_kody,halanlarym,nahar_tayarlansy,futbal,draw;


    public void dil(Context ctx) {
        db = new Db(MainActivity.ctx);


    }

    public void set_text() {
        db = new Db(MainActivity.ctx);
        if (db.get_dil().equals("tm")) {
            Constants.dil="tm";
            garashylyar=" Garaşylýar";
            kafeny_bahalandyryn="Kafeny bahalandyryň";
            kabul_edildi=" Kabul edildi";
            kabul_edilmedi=" Kabul edilmedi";
            yolda=" Ýolda";
            dostawleno=" Eltip berildi";
            shtuk="sany";
            tm_ru="TM";
            gowy="Gowy";
            erbet="Erbet";
            bahalandy="Siz eýýäm baha berdiňiz";
            kanagatlanarly="Kanagatlanarly";
            oran_gowy="Örän gowy";
            first_page="Tagam24 programasyna Hoş geldiňiz, Siz hakan tagamlaryňyzy zakaz edip wagtyňyzy hoş geçirip bilersiňiz.";
            ajayyp="Ajaýyp";
            tagamy_bahalndyryn="Tagamy Bahalandyryň";
            mynasyp_baha_bermeginizi_hayys="Mynasyp Baha Bermegiňizi haýyş etýäris";
            resepti="Düzümi";
            adynyz="Adyňyz";
            tassykla="Tassykla";
            belginiz="Telefon Belgiňiz";
            salgynyz="Salgyňyz";
            tayyarlansy="Taýýarlanşy";
            unregister = "Ulgamdan çykmak";
            telefon_belginizi_girizini = "telefon belgiňizi giriziň";
            salgynyzy_girizin = "salgyňyzy giriziň";
            adynyzy_girizin = "adyňyzy giriziň";
            registrasiya = "Ulgama Girmek";
            tagam_eltip_bermek = "Eltip Bermek Hyzmaty";
            maglumat_yok = "maglumat ýok";
            gumenje="Güýmenje";
            sahsy_otag = "Şahsy otag";
            belgini_dogry_girizin = "belgini dogry giriziň";
            belgini_tassykla = "Telefon Belgini tassyklamak";
            kody_girizin = "kody giriziň";
            Eltip_Bermek_Hyzmaty1 = "Saylanan tagamlary tiz,amatly,ynamly eltip bermek hyzmaty";
            Arzanladyshlar = "Arzanladyslar";
            Arzanladyshlar1 = "Arzanladyş nahar aksiyalary";
            Kafe_Bar = "Kafe-Bar";
            Kafe_Bar1 = "Kafeler we Barlar barada maglumatlar";
            Toy_Hymzatlar = "Toy Hyzmatlary";
            Toy_Hymzatlar1 = "Toy mekanlary we toy hyzmatlarynyn ahli gornusleri";
            Sushi = "Suşi";
            zakaz_kona="Zakaz Hasaba Alynan Salga";
            zakaz_taza="Zakaz Täze Salga";
            Pizza = "Pizza";
            Burger = "Burger";
            Tiz_tagam = "Tiz tagam";
            Shashlyk = "Şaşlyk";
            Corba = "Çorba";
            Balyk = "Balyk";
            Salat = "Salat";
            Desert = "Desert";
            futbal="Futbol";
            Buz_gaymak = "Buz gaýmak";
            Ichgi = "Içgi";
            manat = "manat";
            Irdenlik = "Irdenlik";
            Gunortanlyk = "Günortanlyk";
            Gozleg = "Gözleg...";
            guymenje1 = "Güýmenje";
            guymenje2 = "Gyzykly maglumatlar bilen wagtyňyzy gowy geçiriň";
            yza = "yza";
            dili_sayla = "Dili Saýla";
            tm_dili = "Türkmen dili";
            ru_dili = "Rus dili";
            Kombo = "Kombo";
            Gyzgyn_nahar = "Gyzgyn nahar";
            Hamyrly_nahar = "Hamyrmly nahar";
            Garnir = "Garnir";
            Mangal = "Mangal";
            nahar_tayarlansy="Tagam taýýarlanşy";
            Doner = "Döner";
            tema = "Temanyň reňkini saýlaň";
            yapyk="Ýapyk";
            min_zakaz="Iň az zakaz";
            txt_status_order="Size köp minnetdarlyk bildirýäs Tagam24 programmasynyň üsti bilen zakaz edeňiz üçin.Siziň zakazynyňyz iö gysga wagtda eltip beriler we zakazyň ýagdaýyny meniň zakazym bölüminde görüp bilersiňiz";
            cafe="Kafelar";
            tagamlar="Tagamlar";
            beylekiler="Beýlekiler";
            gyzyklandyrar="Sizi belki gyzyklandyrar";
            eltip_bermek="Eltip bermek";
            zakazy_tassykla="Zakazy tassykla";
            karzina="Sebet";
            halanlarym="Halanlarym";
            welcome="Sizi Tagam24 agzalygyna hoş geldiňiz soraglar üçin admiina ýüz tutyp bilersiňiz";
            tassyklama_kody="Siziň telefon belgiňize ugradylan tassyklama kody girizmegiňizi haýyş etýäris!";
            menin_zakazym="Meniň Zakazym";
            draw="Deňe deň";

        } else {
            Constants.dil="ru";
            garashylyar=" Ожидается";
            kabul_edildi=" Принято";
            kabul_edilmedi=" Отказано";
            yolda=" В пути";
            dostawleno=" Доставлено";
            shtuk="штук";
            tm_ru="RU";
            gowy="Хорошо";
            tassykla="Подтвердить";
            erbet="Плохой";
            oran_gowy="Отлично";
            kafeny_bahalandyryn="Оцените кафе";
            bahalandy="Вы уже оценили";
            kanagatlanarly="Удовлетворительное";
            ajayyp="Удивительный";
            min_zakaz="Минимальный заказ";
            tagamy_bahalndyryn="Пожалуйста, оцените еду";
            mynasyp_baha_bermeginizi_hayys="Пожалуйста, оцените дастоино";
            futbal="Футбол";
            draw="Ничия";
            first_page="Добро пожаловать в приложение Tagam24, где вы можете заказать лучшие блюда в Туркменистане и приятно провести время.";
            menin_zakazym="Mои заказы";
            resepti="Рецепты";
            zakaz_kona="Заказ На Зарегистрированный Адрес";
            zakaz_taza="Заказ На Новый Адрес";
            tayyarlansy="Приготовление";
            txt_status_order="Большое спасибо за заказ с помощью приложения Tagam24. Ваш заказ будет доставлен в кратчайшие сроки и вы можете просмотреть статус вашего заказа в списке заказов.";
            unregister = "Выйти из системы";
            telefon_belginizi_girizini = "введите ваш номер";
            salgynyzy_girizin = "введите ваш адрес";
            adynyzy_girizin = "введите Ваше имя";
            registrasiya = "Регистрация";
            tagam_eltip_bermek = "Доставка Еды";
            maglumat_yok = "нет данных";
            sahsy_otag = "Личная комната";
            belgini_dogry_girizin = "введите правильный номер";
            belgini_tassykla = "Подтвердить Номер";
            kody_girizin = "введите пароль";
            Eltip_Bermek_Hyzmaty1 = "Быстрая, надежная, доступная доставка еды";
            Arzanladyshlar = "Акция";
            Arzanladyshlar1 = "Акция в блюдах";
            Kafe_Bar = "Кафе Бар";
            manat = "манат";
            salgynyz="Ваш адрес";
            adynyz="Ваше имя";
            Kafe_Bar1 = "Информация о Кафе и Барах";
            Toy_Hymzatlar = "Свадебные услуги";
            Toy_Hymzatlar1 = "Ресторан для свадебных церемонииб и оформления свадебной церемонии";
            Sushi = "Суши";
            Pizza = "Пицца";
            Burger = "Бургер";
            Tiz_tagam = "Фастфуд";
            Shashlyk = "Шашлык";
            Corba = "Суп";
            Balyk = "Рыба";
            Salat = "Салат";
            Desert = "Десерт";
            Buz_gaymak = "Марожни";
            Ichgi = "Напиток";
            Irdenlik = "Завтрак";
            Gunortanlyk = "Обед";
            Gozleg = "Поиск...";
            guymenje1 = "Увлечения";
            guymenje2 = "Хорошо провести время с интересной информацией";
            yza = "назад";
            dili_sayla = "Выберите Язык";
            tm_dili = "Туркменский язык";
            ru_dili = "Русский язык";
            tema = "Bыберите язык темы";
            Kombo = "комбо";
            Gyzgyn_nahar = "Горячие блюда";
            Hamyrly_nahar = "Мучные блюда";
            Garnir = "Гарнир";
            Mangal = "Mangal";
            Doner = "Донер";
            cafe="Кафе";
            nahar_tayarlansy="Рецепты еды";
            yapyk="Закрыто";
            beylekiler="Другие";
            tagamlar="Блюда";
            gyzyklandyrar="Может вас заинтересует";
            eltip_bermek="Доставка";
            zakazy_tassykla="Подтвердите ваш заказ";
            karzina="Kорзина";
            halanlarym="Избранные";
            belginiz="Ваш номер";
            welcome="Добро пожаловать к пользователю tagam24, если у вас есть какие-либо вопросы, вы можете связаться с администратором";
            tassyklama_kody="Код подтверждения будет отправлен на ваш номер телефона, введите код здесь!";
        }
    }
}

