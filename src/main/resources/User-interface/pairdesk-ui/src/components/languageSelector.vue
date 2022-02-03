<template>
  <div style="position: absolute;
  top: 8px;
  right: 16px;
  font-size: 18px;"
       name="flags">


    <span style="cursor:pointer" class="font-weight-light" @click="$i18n.locale = 'en'">English </span>
    <img
        src="https://flagcdn.com/16x12/gb.png"
        srcset="https://flagcdn.com/32x24/gb.png 2x,
    https://flagcdn.com/48x36/gb.png 3x"
        width="16"
        height="12"
        alt="United Kingdom">
    <span style="cursor:pointer; padding-left: 5px" class="font-weigh-light" @click="$i18n.locale = 'fr'">Français </span>

    <img
        src="https://flagcdn.com/16x12/mf.png"
        srcset="https://flagcdn.com/32x24/mf.png 2x,
    https://flagcdn.com/48x36/mf.png 3x"
        width="16"
        height="12"
        alt="Saint Martin">

  </div>

</template>