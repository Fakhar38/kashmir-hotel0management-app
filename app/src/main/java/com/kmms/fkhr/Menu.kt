package com.kmms.fkhr


class Menu(
    val title: String
)
{

    companion object {

        //fun getMenusFromFile(filename: String, context: Context): ArrayList<Menu> {
       /*     val menuList = ArrayList<Menu>()

            try {
                // Load data
             /*   val jsonString = loadJsonFromAsset("menus.json", context)
                val json = JSONObject(jsonString)
                val recipes = json.getJSONArray("menus")

                // Get Recipe objects from data
          //      (0 until menus.length()).mapTo(menuList) {
            //        Menu(menus.getJSONObject(it).getString("title"))
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return menuList
        }

        private fun loadJsonFromAsset(filename: String, context: Context): String? {
            var json: String? = null
*/
            try {
                val inputStream = context.assets.open(filename)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
  //              json = kotlin.run { String(buffer, Charsets.UTF_8) }
            } catch (ex: java.io.IOException) {
                ex.printStackTrace()
                return null
            }

            return json*/
       // }
    }
}