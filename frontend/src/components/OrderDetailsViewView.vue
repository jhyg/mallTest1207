<template>

    <v-data-table
        :headers="headers"
        :items="orderDetailsView"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'OrderDetailsViewView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            orderDetailsView : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/orderDetailsViews'))

            temp.data._embedded.orderDetailsViews.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.orderDetailsView = temp.data._embedded.orderDetailsViews;
        },
        methods: {
        }
    }
</script>

