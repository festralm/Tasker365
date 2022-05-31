<template>
  <DefaultLayout>
    <template v-slot:content>
      <div style="width: 100%; display: flex; align-items: center; justify-content: center; margin: 110px 0">
        <iframe src="https://docs.google.com/presentation/d/e/2PACX-1vRyFLZ0p15WUCxSKQWvqMKZMADATPF9tq_hv1TLH7kNDpJVnH4Bx8RS7V-aVZ2Hda_aRfVXavCY-bOM/embed?start=false&loop=true&delayms=3000" frameborder="0" width="900" height="542" allowfullscreen="true" mozallowfullscreen="true" webkitallowfullscreen="true"></iframe>
      </div>
    </template>
  </DefaultLayout>
</template>

<script>
import DefaultLayout from "@/layouts/DefaultLayout";
export default {
  name: 'HomePage',
  components: {DefaultLayout},
}
</script>

<style>

</style>