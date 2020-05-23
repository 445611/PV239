package cz.muni.fi.pv239.dailyyummies.menu

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cz.muni.fi.pv239.dailyyummies.R
import cz.muni.fi.pv239.dailyyummies.menu.restaurant.MenuAdapter
import cz.muni.fi.pv239.dailyyummies.service.networking.data.RestaurantHolder
import cz.muni.fi.pv239.dailyyummies.utils.inflate
import kotlinx.android.synthetic.main.menu_restaurant_row.view.*


class RestaurantAdapter(private val restaurants: List<RestaurantHolder>) :
    RecyclerView.Adapter<RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val row = parent.inflate(R.layout.menu_restaurant_row)
        return RestaurantViewHolder(row)
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(restaurants[position])
    }

}

class RestaurantViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: RestaurantHolder) {
        view.restaurant_name.text = item.restaurant.name
        view.userRating.text = item.restaurant.rating.userRating.toString()
        view.userRatingText.text = item.restaurant.rating.userRatingText
        view.restaurant_distance.text = item.restaurant.distance.toString()
        view.restaurant_menu.layoutManager = LinearLayoutManager(view.context)
        view.restaurant_menu.adapter = MenuAdapter(item.restaurant.menu.dishes)

        toggleExpandButton(view, item)
        view.restaurant_menu.visibility = if (item.isExpanded) View.VISIBLE else View.GONE
    }

    private fun toggleExpandButton(view: View, item: RestaurantHolder) {
        view.expandButton.rotation = if (item.isExpanded) 180f else 0f
        view.expandButton.setOnClickListener {
                if (item.isExpanded) {
                    view.restaurant_menu.visibility = View.GONE
                    item.isExpanded = false
                    view.expandButton.animate().setDuration(200).rotation(0f)
                } else {
                    view.restaurant_menu.visibility = View.VISIBLE
                    item.isExpanded = true
                    view.expandButton.animate().setDuration(200).rotation(180f)
                }
            }
    }

}
