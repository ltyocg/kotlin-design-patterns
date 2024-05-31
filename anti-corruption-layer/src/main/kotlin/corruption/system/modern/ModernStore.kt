package corruption.system.modern

import corruption.system.DataStore
import org.springframework.stereotype.Service

@Service
class ModernStore : DataStore<ModernOrder>()

