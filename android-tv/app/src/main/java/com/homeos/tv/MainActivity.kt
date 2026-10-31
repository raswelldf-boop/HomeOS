package com.homeos.tv

import android.app.Activity
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

/** HomeOS TV first-stage launcher shell. Device values are intentionally shown as unpaired/demo. */
class MainActivity : Activity() {
    private val bg = 0xFFF4F6F8.toInt()
    private val ink = 0xFF202832.toInt()
    private val muted = 0xFF737D89.toInt()
    private val accent = 0xFF5A83A8.toInt()
    private lateinit var content: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = bg
        window.navigationBarColor = bg
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        val scroll = ScrollView(this).apply {
            setBackgroundColor(bg)
            isFillViewport = true
            isVerticalScrollBarEnabled = false
        }
        content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(42), dp(28), dp(42), dp(34))
        }
        scroll.addView(content)
        setContentView(scroll)
        renderDashboard()
    }

    private fun renderDashboard() {
        content.removeAllViews()
        val header = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }
        val brand = column().apply {
            addView(label("HOMEOS", 13f, accent, true))
            addView(label("خانهٔ هوشمند", 28f, ink, true))
        }
        header.addView(brand, LinearLayout.LayoutParams(0, -2, 1f))
        header.addView(label("داشبورد تلویزیون  •  نسخهٔ اولیه", 14f, muted, false))
        content.addView(header)
        spacer(22)

        val hero = card(accent).apply {
            orientation = LinearLayout.VERTICAL
            addView(label("مرکز کنترل خانه", 25f, 0xFFFFFFFF.toInt(), true))
            addView(label("برای اتصال تجهیزات، ابتدا Raspberry Pi و سرویس HomeOS را راه‌اندازی کنید.", 15f, 0xFFEAF1F7.toInt(), false))
            addView(label("وضعیت اتصال: هنوز راه‌اندازی نشده", 14f, 0xFFFFFFFF.toInt(), true))
        }
        content.addView(hero, match())
        spacer(18)
        section("وضعیت خانه", "اطلاعات واقعی پس از اتصال هسته و سنسورها نمایش داده می‌شود")
        val row1 = row()
        row1.addView(tile("◉", "دما و هوا", "بدون سنسور متصل", "اتصال لازم است"), weight())
        row1.addView(tile("☼", "روشنایی", "۰ دستگاه متصل", "اتصال لازم است"), weight())
        row1.addView(tile("⌂", "امنیت", "وضعیت نامشخص", "اتصال لازم است"), weight())
        content.addView(row1, match())
        spacer(12)
        val row2 = row()
        row2.addView(tile("▣", "دوربین‌ها", "هیچ دوربینی ثبت نشده", "افزودن دستگاه"), weight())
        row2.addView(tile("⌁", "پریزها", "۰ دستگاه متصل", "افزودن دستگاه"), weight())
        row2.addView(tile("◌", "شبکه و تجهیزات", "هسته متصل نیست", "بررسی اتصال"), weight())
        content.addView(row2, match())
        spacer(20)
        section("دسترسی سریع", "با کلیدهای جهت‌دار کنترل کنید")
        val actions = row()
        actions.addView(action("مدیا", "پخش از گوشی"), weight())
        actions.addView(action("سناریوها", "اتوماسیون خانه"), weight())
        actions.addView(action("برنامه‌ها", "برنامه‌های تلویزیون"), weight())
        actions.addView(action("تنظیمات", "راه‌اندازی و اتصال"), weight())
        content.addView(actions, match())
        spacer(20)
        content.addView(label("HomeOS TV  •  رابط اولیه؛ دادهٔ زنده و کنترل تجهیزات هنوز فعال نیست.", 12f, muted, false))
    }

    private fun section(title: String, subtitle: String) {
        content.addView(label(title, 20f, ink, true))
        content.addView(label(subtitle, 12f, muted, false))
        spacer(10)
    }

    private fun tile(symbol: String, title: String, status: String, foot: String): LinearLayout = card(0xFFFFFFFF.toInt()).apply {
        addView(label(symbol, 25f, accent, true))
        addView(label(title, 17f, ink, true))
        addView(label(status, 13f, muted, false))
        addView(label(foot, 11f, accent, true))
        isFocusable = true
        isFocusableInTouchMode = true
    }

    private fun action(title: String, detail: String): LinearLayout = card(0xFFFFFFFF.toInt()).apply {
        addView(label(title, 17f, ink, true))
        addView(label(detail, 12f, muted, false))
        isFocusable = true
        isFocusableInTouchMode = true
        setOnClickListener { showMessage("بخش «$title» در مرحلهٔ بعد فعال می‌شود.") }
    }

    private fun card(color: Int) = LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        setPadding(dp(18), dp(15), dp(18), dp(15))
        background = GradientDrawable().apply {
            setColor(color)
            cornerRadius = dp(14).toFloat()
            setStroke(dp(1), 0xFFE5E9ED.toInt())
        }
        elevation = dp(2).toFloat()
    }

    private fun label(text: String, size: Float, color: Int, bold: Boolean) = TextView(this).apply {
        this.text = text
        textSize = size
        setTextColor(color)
        if (bold) setTypeface(typeface, Typeface.BOLD)
        gravity = Gravity.RIGHT
        textDirection = View.TEXT_DIRECTION_RTL
        setPadding(0, dp(3), 0, dp(3))
    }

    private fun row() = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL }
    private fun column() = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
    private fun match() = LinearLayout.LayoutParams(-1, -2)
    private fun weight() = LinearLayout.LayoutParams(0, -2, 1f).apply { marginEnd = dp(10) }
    private fun spacer(height: Int) { content.addView(View(this).apply { this.layoutParams = LinearLayout.LayoutParams(1, dp(height)) }) }
    private fun dp(value: Int) = (value * resources.displayMetrics.density).toInt()

    private fun showMessage(message: String) {
        android.widget.Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) return super.onKeyDown(keyCode, event)
        return super.onKeyDown(keyCode, event)
    }
}
