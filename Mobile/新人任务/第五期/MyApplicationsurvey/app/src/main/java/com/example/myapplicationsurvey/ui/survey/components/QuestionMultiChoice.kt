package com.example.myapplicationsurvey.ui.survey.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationsurvey.data.model.MultiChoiceQuestion
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun QuestionMultiChoice(
    q: MultiChoiceQuestion,
    selected: List<String>,
    onToggle: (String) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)) {
        // 问题标题，稍大字号
        Text(
            text = q.prompt,
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(Modifier.height(8.dp))
        // 辅助说明
        Text(
            text = "Select all that apply.",
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 13.sp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        Spacer(Modifier.height(12.dp))

        val pid = q.id.lowercase()
        val pprompt = q.prompt.lowercase()
        val hasGenre = pid.contains("genre") || pid.contains("genres") ||
                pprompt.contains("genre") || pprompt.contains("genres")
        val hasFavorite = pid.contains("favorite") ||
                pprompt.contains("favorite") ||
                pprompt.contains("favourite")
        val isFavoriteGenres = hasGenre && hasFavorite
        val isExplicitGenres = pid == "music_vibes" ||
                pprompt.contains("what music genres do you like")
        val useFixed = isFavoriteGenres || isExplicitGenres

        val genreOptions = listOf(
            "Pop", "Rock", "Hip Hop / Rap", "R&B (Rhythm and Blues)", "Country",
            "Electronic / Dance / EDM", "Jazz", "Blues", "Classical", "Soul",
            "Funk", "Reggae", "Disco", "Folk", "Metal"
        )
        val options = if (useFixed) genreOptions else q.options

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            options.forEach { opt ->
                val isSelected = selected.contains(opt)
                val containerColor = if (isSelected) {
                    // 选中时：在主题 surface 基础上稍微加深
                    MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
                } else {
                    MaterialTheme.colorScheme.surface
                }
                val borderColor = if (isSelected) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.25f)
                }
                val scale = if (isSelected) 0.98f else 1f

                Card(
                    modifier = Modifier
                        .scale(scale)
                        .clickable { onToggle(opt) },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = containerColor
                    ),
                    border = run {
                        val base = CardDefaults.outlinedCardBorder()
                        BorderStroke(base.width, borderColor)
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = opt,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            ),
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        // 右侧真正的 Checkbox 图标
                        Checkbox(
                            checked = isSelected,
                            onCheckedChange = { onToggle(opt) }
                        )
                    }
                }
            }
        }
    }
}