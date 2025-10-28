<script setup>
defineProps({
  type: {type: String, required: true},
  labelName: {type: String, required: true},
  id: {type: String, required: true},
  placeholder: {type: String, default: ''},
  required: {type: Boolean, default: false},
  value: {type: String, default: ''}
})

const emit = defineEmits(['update:value'])
</script>

<template>
  <div>
    <label :for="id" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
      <slot>{{ labelName }}</slot>
    </label>
    <input
        :type="type"
        :id="id"
        :value="value"
        @input="emit('update:value', $event.target.value)"
        class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
        :placeholder="placeholder" :required="required"/>
  </div>
</template>

<style scoped>

</style>