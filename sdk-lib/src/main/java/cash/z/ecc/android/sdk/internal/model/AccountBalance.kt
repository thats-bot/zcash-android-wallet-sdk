package cash.z.ecc.android.sdk.internal.model

import cash.z.ecc.android.sdk.model.WalletBalance
import cash.z.ecc.android.sdk.model.Zatoshi

internal data class AccountBalance(
    val sapling: WalletBalance,
    val orchard: WalletBalance,
    val unshielded: Zatoshi
) {
    companion object {
        fun new(jni: JniAccountBalance): AccountBalance {
            return AccountBalance(
                sapling =
                    WalletBalance(
                        Zatoshi(jni.saplingTotalBalance),
                        Zatoshi(jni.saplingVerifiedBalance)
                    ),
                orchard =
                    WalletBalance(
                        Zatoshi(jni.orchardTotalBalance),
                        Zatoshi(jni.orchardVerifiedBalance)
                    ),
                unshielded = Zatoshi(jni.unshieldedBalance)
            )
        }
    }
}