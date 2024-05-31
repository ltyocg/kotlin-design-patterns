package corruption.system.legacy

import corruption.system.DataStore
import org.springframework.stereotype.Service

@Service
class LegacyStore : DataStore<LegacyOrder>()

