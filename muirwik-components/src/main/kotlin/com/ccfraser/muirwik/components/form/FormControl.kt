package com.ccfraser.muirwik.components.form

import com.ccfraser.muirwik.components.*
import react.RBuilder
import react.RComponent
import react.RState
import styled.StyledHandler


@JsModule("@material-ui/core/FormControl")
private external val formControlModule: dynamic

@Suppress("UnsafeCastFromDynamic")
private val formControlComponent: RComponent<MFormControlProps, RState> = formControlModule.default

@Suppress("EnumEntryName")
enum class MFormControlVariant {
    standard, outlined, filled
}


/**
 * Div and FieldSet seem to be the most used values for this prop, so we shall enum these... if you need something
 * else, you will have to use the attrs directly.
 */
@Suppress("EnumEntryName")
enum class MFormControlComponent {
    div, fieldSet;

    override fun toString(): String {
        return super.toString().toHyphenCase()
    }
}

@Suppress("EnumEntryName")
enum class MFormControlMargin {
    none, dense, normal
}

external interface MFormControlProps : StyledPropsWithCommonAttributes {
    var disabled: Boolean
    var error: Boolean
    var fullWidth: Boolean
    var hiddenLabel: Boolean
    var required: Boolean
}
var MFormControlProps.margin by EnumPropToString(MFormControlMargin.values())
var MFormControlProps.component by EnumPropToString(MFormControlComponent.values())
var MFormControlProps.variant by EnumPropToString(MFormControlVariant.values())


fun RBuilder.mFormControl(
        component: MFormControlComponent = MFormControlComponent.div,
        disabled: Boolean = false,
        error: Boolean = false,
        fullWidth: Boolean = false,
        margin: MFormControlMargin = MFormControlMargin.none,
        required: Boolean = false,
        variant: MFormControlVariant = MFormControlVariant.standard,
        hiddenLabel: Boolean = false,
        className: String? = null,
        handler: StyledHandler<MFormControlProps>? = null) = createStyled(formControlComponent) {
    attrs.component = component
    attrs.disabled = disabled
    attrs.error = error
    attrs.fullWidth = fullWidth
    attrs.hiddenLabel = hiddenLabel
    attrs.margin = margin
    attrs.required = required
    attrs.variant = variant

    setStyledPropsAndRunHandler(className, handler)
}
