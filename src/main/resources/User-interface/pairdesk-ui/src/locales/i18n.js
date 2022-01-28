import {createI18n} from "vue-i18n";

import en from "./en.json"
import fr from "./fr.json"

const i18n = createI18n({
    legacy: false,
    locale: "en" ,
    globalInjection: true,
    messages: {
        en: en,
        fr: fr,


    }

});

export default i18n;