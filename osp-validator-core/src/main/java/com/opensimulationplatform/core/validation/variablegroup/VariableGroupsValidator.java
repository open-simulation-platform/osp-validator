package com.opensimulationplatform.core.validation.variablegroup;

import com.opensimulationplatform.core.model.modeldescription.variablegroup.VariableGroup;
import com.opensimulationplatform.core.validation.ValidationError;
import com.opensimulationplatform.core.validation.Validator;
import com.opensimulationplatform.core.validation.variablegroup.angularacceleration.AngularAccelerationValidator;
import com.opensimulationplatform.core.validation.variablegroup.angulardisplacement.AngularDisplacementValidator;
import com.opensimulationplatform.core.validation.variablegroup.angularmechanicalport.AngularMechanicalPortValidator;
import com.opensimulationplatform.core.validation.variablegroup.angularmechanicalquasiport.AngularMechanicalQuasiPortValidator;
import com.opensimulationplatform.core.validation.variablegroup.angularvelocity.AngularVelocityValidator;
import com.opensimulationplatform.core.validation.variablegroup.charge.ChargeValidator;
import com.opensimulationplatform.core.validation.variablegroup.current.CurrentValidator;
import com.opensimulationplatform.core.validation.variablegroup.electromagneticport.ElectromagneticPortValidator;
import com.opensimulationplatform.core.validation.variablegroup.electromagneticquasiport.ElectromagneticQuasiPortValidator;
import com.opensimulationplatform.core.validation.variablegroup.force.ForceValidator;
import com.opensimulationplatform.core.validation.variablegroup.hydraulicport.HydraulicPortValidator;
import com.opensimulationplatform.core.validation.variablegroup.hydraulicquasiport.HydraulicQuasiPortValidator;
import com.opensimulationplatform.core.validation.variablegroup.linearacceleration.LinearAccelerationValidator;
import com.opensimulationplatform.core.validation.variablegroup.lineardisplacement.LinearDisplacementValidator;
import com.opensimulationplatform.core.validation.variablegroup.linearmechanicalport.LinearMechanicalPortValidator;
import com.opensimulationplatform.core.validation.variablegroup.linearmechanicalquasiport.LinearMechanicalQuasiPortValidator;
import com.opensimulationplatform.core.validation.variablegroup.linearvelocity.LinearVelocityValidator;
import com.opensimulationplatform.core.validation.variablegroup.pressure.PressureValidator;
import com.opensimulationplatform.core.validation.variablegroup.torque.TorqueValidator;
import com.opensimulationplatform.core.validation.variablegroup.voltage.VoltageValidator;
import com.opensimulationplatform.core.validation.variablegroup.volume.VolumeValidator;
import com.opensimulationplatform.core.validation.variablegroup.volumeflowrate.VolumeFlowRateValidator;

import java.util.Arrays;
import java.util.List;

public class VariableGroupsValidator extends Validator<VariableGroup> {
  private final AngularDisplacementValidator angularDisplacementValidator = new AngularDisplacementValidator();
  private final AngularVelocityValidator angularVelocityValidator = new AngularVelocityValidator();
  private final ChargeValidator chargeValidator = new ChargeValidator();
  private final CurrentValidator currentValidator = new CurrentValidator();
  private final ForceValidator forceValidator = new ForceValidator();
  private final LinearDisplacementValidator linearDisplacementValidator = new LinearDisplacementValidator();
  private final LinearVelocityValidator linearVelocityValidator = new LinearVelocityValidator();
  private final PressureValidator pressureValidator = new PressureValidator();
  private final TorqueValidator torqueValidator = new TorqueValidator();
  private final VoltageValidator voltageValidator = new VoltageValidator();
  private final VolumeValidator volumeValidator = new VolumeValidator();
  private final VolumeFlowRateValidator volumeFlowRateValidator = new VolumeFlowRateValidator();
  private final LinearMechanicalPortValidator linearMechanicalPortValidator = new LinearMechanicalPortValidator();
  private final AngularMechanicalPortValidator angularMechanicalPortValidator = new AngularMechanicalPortValidator();
  private final ElectromagneticPortValidator electromagneticPortValidator = new ElectromagneticPortValidator();
  private final HydraulicPortValidator hydraulicPortValidator = new HydraulicPortValidator();
  private final LinearMechanicalQuasiPortValidator linearMechanicalQuasiPortValidator = new LinearMechanicalQuasiPortValidator();
  private final AngularMechanicalQuasiPortValidator angularMechanicalQuasiPortValidator = new AngularMechanicalQuasiPortValidator();
  private final ElectromagneticQuasiPortValidator electromagneticQuasiPortValidator = new ElectromagneticQuasiPortValidator();
  private final HydraulicQuasiPortValidator hydraulicQuasiPortValidator = new HydraulicQuasiPortValidator();
  private final LinearAccelerationValidator linearAccelerationValidator = new LinearAccelerationValidator();
  private final AngularAccelerationValidator angularAccelerationValidator = new AngularAccelerationValidator();

  private final VE_VariableGroup_1 ve_variableGroup_1 = new VE_VariableGroup_1();
  private final VE_VariableGroup_2 ve_variableGroup_2 = new VE_VariableGroup_2();
  private final VE_VariableGroup_3 ve_variableGroup_3 = new VE_VariableGroup_3();

  @Override
  protected List<Validator<?>> getValidators() {
    return Arrays.asList(
        angularDisplacementValidator,
        angularVelocityValidator,
        chargeValidator,
        currentValidator,
        forceValidator,
        linearDisplacementValidator,
        linearVelocityValidator,
        pressureValidator,
        torqueValidator,
        voltageValidator,
        volumeValidator,
        volumeFlowRateValidator,
        linearMechanicalPortValidator,
        angularMechanicalPortValidator,
        electromagneticPortValidator,
        hydraulicPortValidator,
        linearMechanicalQuasiPortValidator,
        angularMechanicalQuasiPortValidator,
        electromagneticQuasiPortValidator,
        hydraulicQuasiPortValidator,
        linearAccelerationValidator,
        angularAccelerationValidator
    );
  }

  @Override
  protected List<ValidationError<?>> getValidationErrors() {
    return Arrays.asList(
        ve_variableGroup_1,
        ve_variableGroup_2,
        ve_variableGroup_3
    );
  }
}
