package pl.msiwak.recruitmentapp.util.error

sealed class Failure

sealed class ListFailure: Failure(){
    object GetDataFailure: ListFailure()
    object GetDataFromLocalDbFailure: ListFailure()
}