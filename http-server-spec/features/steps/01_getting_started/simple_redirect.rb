require "./features/steps/shared.rb"

class Spinach::Features::SimpleRedirect < Spinach::FeatureSteps
  include Shared::Standard

  step 'I make an GET request to "/redirect"' do
    @response = Requests.get("/redirect")
  end

  step 'my response should have a location header with the "http://0.0.0.0:5000/simple_get" URI' do
    expect(@response.location).to eq("#{PROTOCOL}://#{HOSTNAME}:#{PORT}/simple_get")
  end
end
