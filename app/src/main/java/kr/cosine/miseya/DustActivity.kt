package kr.cosine.miseya

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kr.cosine.miseya.databinding.ActivityDustBinding
import kr.cosine.miseya.util.DustUtil
import kr.cosine.miseya.view.model.DustViewModel
import kr.cosine.miseya.view.model.factory.DustViewModelFactory

class DustActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDustBinding.inflate(layoutInflater) }

    private val dustViewModel: DustViewModel by viewModels { DustViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        registerDustSpinner()
        registerViewModelEvent()
    }

    private fun registerDustSpinner() = with(binding) {
        sidoSpinnerView.setOnSpinnerItemSelectedListener<String> { _, _, _, sidoName ->
            stationSpinnerView.clearSelectedItem()
            dustViewModel.setStation(null)
            dustViewModel.setSido(sidoName)
        }
        stationSpinnerView.setOnSpinnerItemSelectedListener<String> { _, _, _, stationName ->
            dustViewModel.setStation(stationName)
        }
    }

    private fun registerViewModelEvent() = with(binding) {
        dustViewModel.sidoEvent.observe(this@DustActivity) { stations ->
            val stationNames = stations.map { it.stationName }
            stationSpinnerView.setItems(stationNames)
        }
        dustViewModel.stationEvent.observe(this@DustActivity) { station ->
            if (station == null) {
                setDustInfo(
                    getString(R.string.default_location_description),
                    "",
                    getString(R.string.default_dust_value)
                )
            } else {
                val pm10Value = station.pm10Value
                setDustInfo(
                    getString(R.string.location_format, station.sidoName, station.stationName),
                    DustUtil.getStatus(pm10Value),
                    getString(R.string.dust_value_format, pm10Value ?: "-")
                )
            }
        }
    }

    private fun setDustInfo(locationText: String, dustStatus: String, dustValue: String) = with(binding) {
        locationTextView.text = locationText
        dustStatusTextView.text = dustStatus
        dustValueTextView.text = dustValue
    }
}