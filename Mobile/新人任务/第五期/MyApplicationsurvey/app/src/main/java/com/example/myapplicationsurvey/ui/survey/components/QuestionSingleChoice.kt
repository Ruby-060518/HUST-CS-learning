package com.example.myapplicationsurvey.ui.survey.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplicationsurvey.data.model.SingleChoiceQuestion

@Composable
fun QuestionSingleChoice(q: SingleChoiceQuestion, selected: String?, onSelect: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // 问题标题
        Text(
            text = q.prompt, style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 20.sp, fontWeight = FontWeight.SemiBold
            )
        )

        Spacer(Modifier.height(12.dp))

        q.options.forEach { opt ->
            val isSelected = selected == opt
            val containerColor = if (isSelected) {
                // 与多选题类似：选中时稍微加深，但仍使用当前主题颜色
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

            val baseBorder = CardDefaults.outlinedCardBorder()

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .scale(scale)
                    .clickable { onSelect(opt) },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = containerColor
                ),
                border = BorderStroke(baseBorder.width, borderColor)
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
                            fontSize = 16.sp, fontWeight = FontWeight.Medium
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    RadioButton(
                        selected = isSelected, onClick = { onSelect(opt) })
                }
            }
        }
    }
}
