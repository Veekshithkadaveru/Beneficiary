// BeneficiaryRepository.kt
import android.content.Context
import com.example.beneficiary.model.Beneficiary
import com.example.beneficiary.repository.loadJSONFromAsset
import com.example.beneficiary.repository.parseBeneficiaries

class BeneficiaryRepository(private val context: Context) {

    fun loadBeneficiaries(): List<Beneficiary> {
        val jsonString = loadJSONFromAsset(context, "Beneficiaries.json")
        return jsonString?.let { parseBeneficiaries(it) } ?: emptyList()
    }
}
