<script setup>
    import { onMounted, ref } from 'vue'
    import { RecipeService } from '../services/RecipeService.js'

    const recipeList = ref([]);
    const loadRecipeList = async () => {
        try {
            recipeList.value = await RecipeService.select(null);
        }catch(error) {
            console.error('データ取得に失敗しました。')
        }
    };
    onMounted(loadRecipeList);
</script>

<template>
    <h2>レシピ一覧</h2>
    <ul>
        <li v-for="recipe in recipeList" :key="recipe.id">
            {{recipe.name}}: {{recipe.description}}
        </li>
    </ul>
</template>

<style scoped></style>