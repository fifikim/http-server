require "./features/steps/shared.rb"

class Spinach::Features::SimpleHead < Spinach::FeatureSteps
  include Shared::Standard

  step 'I make a HEAD request to "/simple_get"' do
    @response = Requests.head("/simple_get")
  end

  step 'I make a HEAD request to "/head_request"' do
    @response = Requests.head("/head_request")
  end
end
