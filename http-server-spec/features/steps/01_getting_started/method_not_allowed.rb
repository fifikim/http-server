require "./features/steps/shared.rb"

class Spinach::Features::MethodNotAllowed < Spinach::FeatureSteps
  include Shared::Standard

  step 'I make an GET request to "/head_request"' do
    @response = Requests.get("/head_request")
  end

  step 'my response should have allowed headers of HEAD, OPTIONS' do
    expect(@response.allowed_headers).to contain_exactly("HEAD", "OPTIONS")
  end
end
