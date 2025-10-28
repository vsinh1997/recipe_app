<script setup>
import {onMounted, ref} from 'vue'
import {RecipeService} from '../services/RecipeService.js'
import Header from "../components/Header.vue";
import Button from '../components/Button.vue'
import TextField from "../components/TextField.vue";

const recipeList = ref([]);

const recipeNameValue = ref('');
const loadRecipeList = async () => {
  try {
    recipeList.value = await RecipeService.select(null);
  } catch (error) {
    console.error('データ取得に失敗しました。')
  }
};
onMounted(loadRecipeList);

const clickSearch = () => {
  alert(recipeNameValue.value)
}
</script>

<template>
  <Header header_name="レシピ一覧"/>

  <div class="grid gap-6 mb-6 md:grid-cols-2">
    <TextField id="recipeName" label-name="レシピ名" type="text" v-model="recipeNameValue"/>
    <Button variant="Default" type="button" button-name="検索" @click="clickSearch"/>
  </div>

</template>

<style scoped></style>